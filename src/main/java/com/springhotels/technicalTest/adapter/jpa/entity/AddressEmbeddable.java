package com.springhotels.technicalTest.adapter.jpa.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class AddressEmbeddable {

    private String street;
    private String city;
    private String country;
    private String postalCode;

    public AddressEmbeddable(String street, String city, String country, String postalCode) {
        this.street = street;
        this.city = city;
        this.country = country;
        this.postalCode = postalCode;
    }

    protected AddressEmbeddable() {}

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPostalCode() {
        return postalCode;
    }
}
