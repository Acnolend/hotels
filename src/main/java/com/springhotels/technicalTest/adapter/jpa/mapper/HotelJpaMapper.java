package com.springhotels.technicalTest.adapter.jpa.mapper;

import org.springframework.stereotype.Component;

import com.springhotels.technicalTest.adapter.jpa.entity.AddressEmbeddable;
import com.springhotels.technicalTest.adapter.jpa.entity.HotelJpaEntity;
import com.springhotels.technicalTest.domain.entity.Hotel;
import com.springhotels.technicalTest.domain.valueobject.HotelAddress;
import com.springhotels.technicalTest.domain.valueobject.HotelName;
import com.springhotels.technicalTest.domain.enumerate.HotelStars;

@Component
public class HotelJpaMapper {

    public Hotel toDomain(HotelJpaEntity entity) {
        return new Hotel(
                entity.getId(),
                new HotelName(entity.getName()),
                HotelStars.fromValue(entity.getStars()),
                new HotelAddress(
                        entity.getAddress().getStreet(),
                        entity.getAddress().getCity(),
                        entity.getAddress().getCountry(),
                        entity.getAddress().getPostalCode()
                )
        );
    }

    public HotelJpaEntity toJpa(Hotel hotel) {
        HotelJpaEntity entity = new HotelJpaEntity();
        entity.setId(hotel.getId());
        entity.setName(hotel.getName().getName());
        entity.setStars(hotel.getStars().getValue());
        AddressEmbeddable adr = new AddressEmbeddable();
        adr.setStreet(hotel.getAddress().getStreet());
        adr.setCity(hotel.getAddress().getCity());
        adr.setCountry(hotel.getAddress().getCountry());
        adr.setPostalCode(hotel.getAddress().getPostalCode());
        entity.setAddress(adr);
        return entity;
    }
}
