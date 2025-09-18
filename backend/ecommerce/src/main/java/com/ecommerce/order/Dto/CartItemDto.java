package com.ecommerce.order.Dto;

/**
 * DTO for the CartItem entity.
 * Contains only the information that the user can insert/modify.
 * Audit fields (id, createdAt, updatedAt) are managed by the system.
 */
public class CartItemDto {

    private Integer cantidad;
    private Long bookId;
    private Long cartId;

    // Constructors
    public CartItemDto() {}

    public CartItemDto(Integer cantidad, Long bookId, Long cartId) {
        this.cantidad = cantidad;
        this.bookId = bookId;
        this.cartId = cartId;
    }

    // Getters and Setters
    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }
}