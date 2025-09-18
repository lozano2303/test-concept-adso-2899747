package com.ecommerce.catalog.Entity;

import com.ecommerce.common.Entity.ABaseEntity;
import com.ecommerce.user.Entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Entidad Review que representa una reseña de un libro.
 * Extiende ABaseEntity para heredar campos comunes como ID y timestamps.
 */
@Entity
@Table(name = "review")
public class Review extends ABaseEntity {

    @Column(columnDefinition = "TEXT")
    private String contenido;

    @Column
    private Integer rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    // Constructors
    public Review() {}

    public Review(String contenido, Integer rating, User user, Book book) {
        this.contenido = contenido;
        this.rating = rating;
        this.user = user;
        this.book = book;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}