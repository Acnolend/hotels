package com.springhotels.technicalTest.adapter.jpa.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "hotels")
public class HotelJpaEntity {

    @Id
    private UUID id;

    private String name;

    private int stars;

    @Embedded
    private AddressEmbeddable address;

    public HotelJpaEntity() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public AddressEmbeddable getAddress() {
        return address;
    }

    public void setAddress(AddressEmbeddable address) {
        this.address = address;
    }
}

