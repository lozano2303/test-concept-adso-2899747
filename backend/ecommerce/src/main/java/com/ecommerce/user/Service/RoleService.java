package com.ecommerce.user.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecommerce.common.Service.ABaseService;
import com.ecommerce.user.Entity.Role;
import com.ecommerce.user.IRepository.IRoleRepository;
import com.ecommerce.user.IService.IRoleService;

/**
 * Implementación del servicio para la entidad Role.
 * Extiende ABaseService para operaciones CRUD básicas.
 */
@Service
public class RoleService extends ABaseService<Role, Long> implements IRoleService {

    private final IRoleRepository roleRepository;

    public RoleService(IRoleRepository roleRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Role> findByNombre(String nombre) {
        return roleRepository.findByNombre(nombre);
    }

    @Override
    public boolean existsByNombre(String nombre) {
        return roleRepository.existsByNombre(nombre);
    }
}