package com.springhotels.technicalTest.application.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Hotel> getHotels(Pageable pageable) {
        return this.repository.fetchAll(pageable);
    }

    @Override
    public Page<Hotel> getHotelsByCity(Pageable pageable, String city) {
        return this.repository.fetchByCity(pageable, city);
    }
    
}
