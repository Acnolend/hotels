package com.springhotels.technicalTest;

import java.util.UUID;

import com.springhotels.technicalTest.adapter.jpa.entity.AddressEmbeddable;
import com.springhotels.technicalTest.adapter.jpa.entity.HotelJpaEntity;

public class TestUtils {

    public static HotelJpaEntity jpaHotel(String name) {
        AddressEmbeddable a = new AddressEmbeddable("Street", "City", "Country", "12345");
        HotelJpaEntity h = new HotelJpaEntity(UUID.randomUUID(), name, 4, a);
        return h;
    }
}
