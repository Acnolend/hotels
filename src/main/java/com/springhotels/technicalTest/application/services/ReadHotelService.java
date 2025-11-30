package com.springhotels.technicalTest.application.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springhotels.technicalTest.application.repository.HotelRepository;
import com.springhotels.technicalTest.application.usecases.ReadHotelUseCase;
import com.springhotels.technicalTest.domain.entity.Hotel;

@Service
public class ReadHotelService implements ReadHotelUseCase {

    @Autowired
    private HotelRepository repository;

    @Override
    public Hotel getHotel(UUID id) {
       return this.repository.fetch(id);
    }

    @Override
    public List<Hotel> getHotels() {
        return this.repository.fetchAll();
    }

    @Override
    public List<Hotel> getHotelsByCity(String city) {
        return this.repository.fetchByCity(city);
    }
    
}
