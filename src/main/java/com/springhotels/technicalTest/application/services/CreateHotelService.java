package com.springhotels.technicalTest.application.services;

import org.springframework.stereotype.Service;

import com.springhotels.technicalTest.application.repository.HotelRepository;
import com.springhotels.technicalTest.application.usecases.CreateHotelUseCase;
import com.springhotels.technicalTest.domain.entity.Hotel;
import com.springhotels.technicalTest.domain.enumerate.HotelStars;
import com.springhotels.technicalTest.domain.valueobject.HotelAddress;
import com.springhotels.technicalTest.domain.valueobject.HotelName;

@Service
public class CreateHotelService implements CreateHotelUseCase {

    private HotelRepository repository;

    public CreateHotelService(HotelRepository repository) {
        this.repository = repository;
    }

    @Override
    public Hotel createHotel(HotelName name, HotelStars stars, HotelAddress address) {
        return this.repository.save(new Hotel(name, stars, address));
    }
}
