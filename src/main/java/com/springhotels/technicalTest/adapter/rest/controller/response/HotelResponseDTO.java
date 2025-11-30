package com.springhotels.technicalTest.adapter.rest.controller.response;

import com.springhotels.technicalTest.adapter.rest.controller.request.AddressDTO;

public class HotelResponseDTO {

    private String id;
    private String name;
    private int stars;
    private AddressDTO address;

    public HotelResponseDTO(String id, String name, int stars, AddressDTO address) {
        this.id = id;
        this.name = name;
        this.stars = stars;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStars() {
        return stars;
    }

    public AddressDTO getAddress() {
        return address;
    }


}

