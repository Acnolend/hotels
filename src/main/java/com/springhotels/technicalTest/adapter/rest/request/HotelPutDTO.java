package com.springhotels.technicalTest.adapter.rest.request;

import jakarta.validation.Valid;

public class HotelPutDTO {

    @Valid
    AddressDTO address;

    public HotelPutDTO(AddressDTO address) {
        this.address = address;
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
                "Hotel={address=%s}",
                this.address.toString());
    }
}
