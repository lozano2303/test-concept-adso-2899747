package com.ecommerce.catalog.Dto;

import java.math.BigDecimal;

/**
 * DTO for the Book entity.
 * Contains only the information that the user can insert/modify.
 * Audit fields (id, createdAt, updatedAt) are managed by the system.
 */
public class BookDto {

    private String titulo;
    private String autor;
    private BigDecimal precio;
    private Integer stock;
    private Long categoryId;

    // Constructors
    public BookDto() {}

    public BookDto(String titulo, String autor, BigDecimal precio, Integer stock, Long categoryId) {
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
        this.stock = stock;
        this.categoryId = categoryId;
    }

    // Getters and Setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}