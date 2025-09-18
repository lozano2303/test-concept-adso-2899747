package com.ecommerce.order.Entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ecommerce.common.Entity.ABaseEntity;
import com.ecommerce.user.Entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entidad Order que representa un pedido.
 * Extiende ABaseEntity para heredar campos comunes como ID y timestamps.
 */
@Entity
@Table(name = "`order`")
public class Order extends ABaseEntity {

    @Column
    private LocalDateTime fecha;

    @Column(precision = 10, scale = 2)
    private BigDecimal total;

    @Column(length = 50)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    // Constructors
    public Order() {}

    public Order(LocalDateTime fecha, BigDecimal total, String estado, User user) {
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}