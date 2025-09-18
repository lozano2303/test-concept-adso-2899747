package com.ecommerce.order.Dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for the Order entity.
 * Contains only the information that the user can insert/modify.
 * Audit fields (id, createdAt, updatedAt) are managed by the system.
 */
public class OrderDto {

    private LocalDateTime fecha;
    private BigDecimal total;
    private String estado;
    private Long userId;

    // Constructors
    public OrderDto() {}

    public OrderDto(LocalDateTime fecha, BigDecimal total, String estado, Long userId) {
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
        this.userId = userId;
    }

    // Getters and Setters
    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}