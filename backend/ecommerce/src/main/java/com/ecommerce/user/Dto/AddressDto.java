package com.ecommerce.user.Dto;

/**
 * DTO for the Address entity.
 * Contains only the information that the user can insert/modify.
 * Audit fields (id, createdAt, updatedAt) are managed by the system.
 */
public class AddressDto {

    private String street;
    private String city;
    private String country;
    private String postalCode;
    private Long userId; // User ID to avoid exposing complete entity (optional, can be null)

    // Constructors
    public AddressDto() {}

    public AddressDto(String street, String city, String country, String postalCode) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
    }

    public AddressDto(String street, String city, String country, String postalCode, Long userId) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
        this.userId = userId;
    }

    // Getters and Setters
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}