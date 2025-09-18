package com.ecommerce.catalog.Dto;

/**
 * DTO for the Review entity.
 * Contains only the information that the user can insert/modify.
 * Audit fields (id, createdAt, updatedAt) are managed by the system.
 */
public class ReviewDto {

    private String contenido;
    private Integer rating;
    private Long userId;
    private Long bookId;

    // Constructors
    public ReviewDto() {}

    public ReviewDto(String contenido, Integer rating, Long userId, Long bookId) {
        this.contenido = contenido;
        this.rating = rating;
        this.userId = userId;
        this.bookId = bookId;
    }

    // Getters and Setters
    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}