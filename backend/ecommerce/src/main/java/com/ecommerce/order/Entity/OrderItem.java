package com.ecommerce.order.Entity;

import java.math.BigDecimal;

import com.ecommerce.catalog.Entity.Book;
import com.ecommerce.common.Entity.ABaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entidad OrderItem que representa un item de un pedido.
 * Extiende ABaseEntity para heredar campos comunes como ID y timestamps.
 */
@Entity
@Table(name = "order_item")
public class OrderItem extends ABaseEntity {

    @Column(nullable = false)
    private Integer cantidad;

    @Column(precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    // Constructors
    public OrderItem() {}

    public OrderItem(Integer cantidad, BigDecimal precioUnitario, Book book, Order order) {
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.book = book;
        this.order = order;
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}