package com.springhotels.technicalTest.application.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springhotels.technicalTest.application.repository.HotelRepository;
import com.springhotels.technicalTest.application.usecases.ReadHotelUseCase;
import com.springhotels.technicalTest.domain.entity.Hotel;

@Service
public class ReadHotelService implements ReadHotelUseCase {

    private HotelRepository repository;

    public ReadHotelService(HotelRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Hotel> getHotel(UUID id) {
       return repository.fetch(id);
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
