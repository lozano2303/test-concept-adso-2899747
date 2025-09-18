package com.ecommerce.order.Dto;

import java.math.BigDecimal;

/**
 * DTO for the OrderItem entity.
 * Contains only the information that the user can insert/modify.
 * Audit fields (id, createdAt, updatedAt) are managed by the system.
 */
public class OrderItemDto {

    private Integer cantidad;
    private BigDecimal precioUnitario;
    private Long bookId;
    private Long orderId;

    // Constructors
    public OrderItemDto() {}

    public OrderItemDto(Integer cantidad, BigDecimal precioUnitario, Long bookId, Long orderId) {
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.bookId = bookId;
        this.orderId = orderId;
    }

    // Getters and Setters
    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}