package com.springhotels.technicalTest;

import java.util.UUID;

import com.springhotels.technicalTest.adapter.jpa.entity.AddressEmbeddable;
import com.springhotels.technicalTest.adapter.jpa.entity.HotelJpaEntity;

public class TestUtils {

    public static HotelJpaEntity jpaHotel(String name) {
        HotelJpaEntity e = new HotelJpaEntity();
        e.setId(UUID.randomUUID());
        e.setName(name);
        e.setStars(4);

        AddressEmbeddable a = new AddressEmbeddable();
        a.setStreet("Street");
        a.setCity("City");
        a.setCountry("Country");
        a.setPostalCode("12345");
        e.setAddress(a);
        return e;
    }
}
