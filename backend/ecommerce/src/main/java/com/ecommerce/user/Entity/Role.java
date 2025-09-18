package com.ecommerce.user.Entity;

import java.util.Set;

import com.ecommerce.common.Entity.ABaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * Entidad Role que representa un rol en el sistema.
 * Extiende ABaseEntity para heredar campos comunes como ID y timestamps.
 */
@Entity
@Table(name = "roles")
public class Role extends ABaseEntity {

    @Column(nullable = false, unique = true, length = 50)
    private String nombre;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private Set<User> users;

    // Constructors
    public Role() {}

    public Role(String nombre) {
        this.nombre = nombre;
    }

    // Getters and Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}