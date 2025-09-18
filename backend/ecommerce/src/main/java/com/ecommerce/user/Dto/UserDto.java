package com.ecommerce.user.Dto;

import java.util.Set;

/**
 * DTO for the User entity.
 * Contains only the information that the user can insert/modify.
 * Audit fields (id, createdAt, updatedAt) are managed by the system.
 */
public class UserDto {

    private String name;
    private String email;
    private String password; // Required for user creation/updates
    private Boolean enabled;
    private Set<Long> roleIds; // Role IDs to avoid exposing complete entities

    // Constructors
    public UserDto() {}

    public UserDto(String name, String email, String password, Boolean enabled, Set<Long> roleIds) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.roleIds = roleIds;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Set<Long> roleIds) {
        this.roleIds = roleIds;
    }
}