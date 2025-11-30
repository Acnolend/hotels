package com.springhotels.technicalTest.application.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springhotels.technicalTest.application.repository.HotelRepository;
import com.springhotels.technicalTest.application.usecases.UpdateHotelUseCase;
import com.springhotels.technicalTest.domain.entity.Hotel;
import com.springhotels.technicalTest.domain.valueobject.HotelAddress;

@Service
public class UpdateHotelService implements UpdateHotelUseCase {

    @Autowired
    private HotelRepository repository;

    @Override
    public Hotel updateHotel(UUID id, HotelAddress address) {
        Hotel hotel = this.repository.fetch(id);
        hotel.setAddress(address);
        repository.save(hotel);
        return hotel;
    }
}
