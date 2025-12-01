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
        AddressEmbeddable adr = entity.getAddress();
        HotelAddress address = new HotelAddress(
                adr.getStreet(),
                adr.getCity(),
                adr.getCountry(),
                adr.getPostalCode()
        );
        return new Hotel(entity.getId(), new HotelName(entity.getName()), HotelStars.fromValue(entity.getStars()), address);
    }

    public HotelJpaEntity toJpa(Hotel hotel) {
        AddressEmbeddable adr = new AddressEmbeddable(
                hotel.getAddress().getStreet(),
                hotel.getAddress().getCity(),
                hotel.getAddress().getCountry(),
                hotel.getAddress().getPostalCode()
        );
        return new HotelJpaEntity(hotel.getId(), hotel.getName().getName(), hotel.getStars().getValue(), adr);
    }
}

