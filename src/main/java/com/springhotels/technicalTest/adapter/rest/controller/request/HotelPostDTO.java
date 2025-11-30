package com.springhotels.technicalTest.adapter.rest.controller.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class HotelPostDTO {

    @NotBlank(message = "Name cannot be blank")
    String name;

    @Min(value = 1, message = "Stars must be between 1 and 5")
    @Max(value = 5, message = "Stars must be between 1 and 5")
    int stars;

    @Valid
    AddressDTO address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return String.format(
                "Hotel={name=%s, stars=%s, address=%s}",
                this.name,
                this.stars,
                this.address.toString());
    }
}
