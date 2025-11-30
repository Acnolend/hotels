package com.springhotels.technicalTest.domain.entity;

import java.util.Objects;
import java.util.UUID;

import com.springhotels.technicalTest.domain.enumerate.HotelStars;
import com.springhotels.technicalTest.domain.valueobject.HotelAddress;
import com.springhotels.technicalTest.domain.valueobject.HotelName;

public class Hotel {

    private static final String ERROR_FIELD_NOT_DEFINED = "%s cannot be null";

    /**
     * The ID of the hotel. It is a computed attribute.
     */
    private UUID id;
    /**
     * The name of the hotel. It is a required attribute.
     */
    private HotelName name;
    /**
     * The stars of the hotel. It is a required attribute.
     */
    private HotelStars stars;
    /**
     * The address of the hotel. It is a required attribute.
     */
    private HotelAddress address;

    public Hotel(HotelName name, HotelStars stars, HotelAddress address) {
        validate(name, "Name");
        validate(stars, "Stars");
        validate(address, "Address");
        this.id = UUID.randomUUID();
        this.name = name;
        this.stars = stars;
        this.address = address;
    }

    /**
     * Restore constructor
     */
    public Hotel(UUID id, HotelName name, HotelStars stars, HotelAddress address) {
        this.id = id;
        this.name = name;
        this.stars = stars;
        this.address = address;
    }

    public UUID getId() {
        return this.id;
    }

    public HotelName getName() {
        return this.name;
    }

    public HotelStars getStars() {
        return this.stars;
    }

    public HotelAddress getAddress() {
        return this.address;
    }

    public void setAddress(HotelAddress address) {
        this.address = address;
    }

    public void validate(Object value, String fieldName) {
        if(value == null) {
            throw new IllegalArgumentException(String.format(ERROR_FIELD_NOT_DEFINED, fieldName));
        }
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null) {
            return false;
        }
        if (this.getClass() != otherObject.getClass()) {
            return false;
        }
        final Hotel otherHotel = (Hotel) otherObject;
        return this.id.equals(otherHotel.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return String.format(
                "Hotel={id=%s, name=%s, stars=%s, address=%s}",
                this.id,
                this.name.toString(),
                this.stars.toString(),
                this.address.toString());
    }
}
