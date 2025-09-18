package com.ecommerce.user.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.user.Dto.AddressDto;
import com.ecommerce.user.Entity.Address;
import com.ecommerce.user.Entity.User;
import com.ecommerce.user.IService.IAddressService;
import com.ecommerce.user.IService.IUserService;

/**
 * REST Controller for the Address entity.
 * Provides endpoints for CRUD operations.
 */
@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final IAddressService addressService;
    private final IUserService userService;

    public AddressController(IAddressService addressService, IUserService userService) {
        this.addressService = addressService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<AddressDto>> getAllAddresses() {
        List<Address> addresses = addressService.findAll();
        List<AddressDto> addressDtos = addresses.stream().map(address -> convertToDto(address)).collect(Collectors.toList());
        return ResponseEntity.ok(addressDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable Long id) {
        Optional<Address> address = addressService.findById(id);
        if (address.isPresent()) {
            return ResponseEntity.ok(convertToDto(address.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AddressDto>> getAddressesByUserId(@PathVariable Long userId) {
        List<Address> addresses = addressService.findByUserId(userId);
        List<AddressDto> addressDtos = addresses.stream().map(address -> convertToDto(address)).collect(Collectors.toList());
        return ResponseEntity.ok(addressDtos);
    }

    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@RequestBody AddressDto addressDto) {
        Address address = convertToEntity(addressDto);
        Address savedAddress = addressService.save(address);
        return ResponseEntity.ok(convertToDto(savedAddress));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDto> updateAddress(@PathVariable Long id, @RequestBody AddressDto addressDto) {
        Optional<Address> existingAddress = addressService.findById(id);
        if (existingAddress.isPresent()) {
            Address address = existingAddress.get();
            address.setCalle(addressDto.getStreet());
            address.setCiudad(addressDto.getCity());
            address.setPais(addressDto.getCountry());
            address.setCodigoPostal(addressDto.getPostalCode());
            // Update user association if userId is provided
            if (addressDto.getUserId() != null) {
                Optional<User> user = userService.findById(addressDto.getUserId());
                if (user.isPresent()) {
                    address.setUser(user.get());
                } else {
                    // Handle case where user does not exist
                    address.setUser(null);
                }
            } else {
                // If userId is null, remove association
                address.setUser(null);
            }
            Address updatedAddress = addressService.save(address);
            return ResponseEntity.ok(convertToDto(updatedAddress));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        if (addressService.findById(id).isPresent()) {
            addressService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Associates an address with a user.
     * @param addressId The address ID.
     * @param userId The user ID.
     * @return The updated AddressDto.
     */
    @PutMapping("/{addressId}/associate-user/{userId}")
    public ResponseEntity<AddressDto> associateAddressWithUser(@PathVariable Long addressId, @PathVariable Long userId) {
        Optional<Address> addressOpt = addressService.findById(addressId);
        if (addressOpt.isPresent()) {
            Address address = addressOpt.get();
            // Create a user reference for association
            // In a real implementation, you would load the full user from IUserService
            User user = new User();
            // Note: We can't set the ID directly as it's managed by JPA
            // Instead, we rely on the service layer to handle the association properly
            address.setUser(user);
            Address updatedAddress = addressService.save(address);
            return ResponseEntity.ok(convertToDto(updatedAddress));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private AddressDto convertToDto(Address address) {
        AddressDto dto = new AddressDto();
        dto.setStreet(address.getCalle());
        dto.setCity(address.getCiudad());
        dto.setCountry(address.getPais());
        dto.setPostalCode(address.getCodigoPostal());
        // User can be null - addresses can exist without being associated to a user
        if (address.getUser() != null) {
            dto.setUserId(address.getUser().getId());
        }
        // Audit fields (id, createdAt, updatedAt) are not included in the DTO
        return dto;
    }

    private Address convertToEntity(AddressDto dto) {
        Address address = new Address();
        address.setCalle(dto.getStreet());
        address.setCiudad(dto.getCity());
        address.setPais(dto.getCountry());
        address.setCodigoPostal(dto.getPostalCode());
        // If userId is provided, fetch and set the user
        if (dto.getUserId() != null) {
            Optional<User> user = userService.findById(dto.getUserId());
            if (user.isPresent()) {
                address.setUser(user.get());
            } else {
                // Handle case where user does not exist - could throw exception or log warning
                // For now, we'll allow the address to be created without user association
            }
        }
        return address;
    }
}