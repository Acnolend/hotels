package com.springhotels.technicalTest.adapter.jpa.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "hotels")
public class HotelJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    private int stars;

    @Embedded
    private AddressEmbeddable address;

    protected HotelJpaEntity() {}

    public HotelJpaEntity(UUID id, String name, int stars, AddressEmbeddable address) {
        this.id = id;
        this.name = name;
        this.stars = stars;
        this.address = address;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStars() {
        return stars;
    }

    public AddressEmbeddable getAddress() {
        return address;
    }
    
}

