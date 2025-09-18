package com.ecommerce.user.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.common.Service.ABaseService;
import com.ecommerce.user.Entity.Address;
import com.ecommerce.user.IRepository.IAddressRepository;
import com.ecommerce.user.IService.IAddressService;

/**
 * Implementación del servicio para la entidad Address.
 * Extiende ABaseService para operaciones CRUD básicas.
 */
@Service
public class AddressService extends ABaseService<Address, Long> implements IAddressService {

    private final IAddressRepository addressRepository;

    public AddressService(IAddressRepository addressRepository) {
        super(addressRepository);
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> findByUserId(Long userId) {
        return addressRepository.findByUserId(userId);
    }
}