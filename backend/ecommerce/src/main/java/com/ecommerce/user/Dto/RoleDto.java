package com.ecommerce.user.Dto;

/**
 * DTO for the Role entity.
 * Contains only the information that the user can insert/modify.
 * Audit fields (id, createdAt, updatedAt) are managed by the system.
 */
public class RoleDto {

    private String name;

    // Constructors
    public RoleDto() {}

    public RoleDto(String name) {
        this.name = name;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}