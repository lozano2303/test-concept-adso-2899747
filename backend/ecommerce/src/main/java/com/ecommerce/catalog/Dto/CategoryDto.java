package com.ecommerce.catalog.Dto;

/**
 * DTO for the Category entity.
 * Contains only the information that the user can insert/modify.
 * Audit fields (id, createdAt, updatedAt) are managed by the system.
 */
public class CategoryDto {

    private String nombre;
    private String descripcion;

    // Constructors
    public CategoryDto() {}

    public CategoryDto(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Getters and Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}