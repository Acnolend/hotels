package com.springhotels.technicalTest.adapter.rest.request;

import com.springhotels.technicalTest.domain.valueobject.HotelAddress;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AddressDTO {

    @NotBlank(message = "Street cannot be blank")
    @Size(min = 2, max = 100, message = "Street must be between 2 and 100 characters")
    String street;

    @NotBlank(message = "City cannot be blank")
    @Size(min = 2, max = 100, message = "City must be between 2 and 100 characters")
    String city;

    @NotBlank(message = "Country cannot be blank")
    @Size(min = 2, max = 100, message = "Country must be between 2 and 100 characters")
    String country;

    @NotBlank(message = "Postal code cannot be blank")
    @Size(min = 2, max = 10, message = "Postal code must be between 2 and 10 characters")
    String postalCode;

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

    public static AddressDTO fromAddress(HotelAddress address) {
        AddressDTO dto = new AddressDTO();
        dto.street = address.getStreet();
        dto.city = address.getCity();
        dto.country = address.getCountry();
        dto.postalCode = address.getPostalCode();
        return dto;
    }

    @Override
    public String toString() {
        return String.format(
                "Address={street=%s, city=%s, country=%s, postalCode=%s}",
                this.street, this.city, this.country, this.postalCode);
    }
}
