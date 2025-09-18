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

import com.ecommerce.user.Dto.UserDto;
import com.ecommerce.user.Entity.User;
import com.ecommerce.user.IService.IUserService;

/**
 * REST Controller for the User entity.
 * Provides endpoints for CRUD operations.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    /**
     * Gets all users.
     * @return List of UserDto.
     */
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.findAll();
        List<UserDto> userDtos = users.stream().map(user -> convertToDto(user)).collect(Collectors.toList());
        return ResponseEntity.ok(userDtos);
    }

    /**
     * Gets a user by ID.
     * @param id The user's ID.
     * @return UserDto if exists, 404 if not.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(convertToDto(user.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Creates a new user.
     * @param userDto The user data.
     * @return The created UserDto.
     */
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User user = convertToEntity(userDto);
        User savedUser = userService.save(user);
        return ResponseEntity.ok(convertToDto(savedUser));
    }

    /**
     * Updates an existing user.
     * @param id The user's ID.
     * @param userDto The new data.
     * @return The updated UserDto, 404 if not exists.
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        Optional<User> existingUser = userService.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            // Update fields (simplified, in production use a mapper)
            user.setNombre(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setEnabled(userDto.getEnabled());
            User updatedUser = userService.save(user);
            return ResponseEntity.ok(convertToDto(updatedUser));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a user by ID.
     * @param id The user's ID.
     * @return 204 if deleted, 404 if not exists.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (userService.findById(id).isPresent()) {
            userService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Helper methods for conversion (in production use a mapper like MapStruct)
    private UserDto convertToDto(User user) {
        UserDto dto = new UserDto();
        dto.setName(user.getNombre());
        dto.setEmail(user.getEmail());
        dto.setEnabled(user.getEnabled());
        // Simplified: do not include complete roles and addresses
        // Audit fields (id, createdAt, updatedAt) are not included in the DTO
        return dto;
    }

    private User convertToEntity(UserDto dto) {
        User user = new User();
        user.setNombre(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setEnabled(dto.getEnabled());
        // Password is included in DTO for creation/updates but not returned in responses
        return user;
    }
}