package com.ecommerce.order.Dto;

/**
 * DTO for the Cart entity.
 * Contains only the information that the user can insert/modify.
 * Audit fields (id, createdAt, updatedAt) are managed by the system.
 */
public class CartDto {

    private Long userId;

    // Constructors
    public CartDto() {}

    public CartDto(Long userId) {
        this.userId = userId;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}