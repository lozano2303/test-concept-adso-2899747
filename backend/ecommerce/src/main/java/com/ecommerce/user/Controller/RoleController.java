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

import com.ecommerce.user.Dto.RoleDto;
import com.ecommerce.user.Entity.Role;
import com.ecommerce.user.IService.IRoleService;

/**
 * REST Controller for the Role entity.
 * Provides endpoints for CRUD operations.
 */
@RestController
@RequestMapping("/api/roles")
public class RoleController {

    private final IRoleService roleService;

    public RoleController(IRoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles() {
        List<Role> roles = roleService.findAll();
        List<RoleDto> roleDtos = roles.stream().map(role -> convertToDto(role)).collect(Collectors.toList());
        return ResponseEntity.ok(roleDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable Long id) {
        Optional<Role> role = roleService.findById(id);
        if (role.isPresent()) {
            return ResponseEntity.ok(convertToDto(role.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<RoleDto> createRole(@RequestBody RoleDto roleDto) {
        Role role = convertToEntity(roleDto);
        Role savedRole = roleService.save(role);
        return ResponseEntity.ok(convertToDto(savedRole));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDto> updateRole(@PathVariable Long id, @RequestBody RoleDto roleDto) {
        Optional<Role> existingRole = roleService.findById(id);
        if (existingRole.isPresent()) {
            Role role = existingRole.get();
            role.setNombre(roleDto.getName());
            Role updatedRole = roleService.save(role);
            return ResponseEntity.ok(convertToDto(updatedRole));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        if (roleService.findById(id).isPresent()) {
            roleService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private RoleDto convertToDto(Role role) {
        RoleDto dto = new RoleDto();
        dto.setName(role.getNombre());
        // Audit fields (id, createdAt, updatedAt) are not included in the DTO
        return dto;
    }

    private Role convertToEntity(RoleDto dto) {
        Role role = new Role();
        role.setNombre(dto.getName());
        return role;
    }
}