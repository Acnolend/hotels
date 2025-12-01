package com.springhotels.technicalTest.application.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.springhotels.technicalTest.application.repository.HotelRepository;
import com.springhotels.technicalTest.application.usecases.UpdateHotelUseCase;
import com.springhotels.technicalTest.domain.entity.Hotel;
import com.springhotels.technicalTest.domain.valueobject.HotelAddress;

@Service
public class UpdateHotelService implements UpdateHotelUseCase {

    private HotelRepository repository;

    public UpdateHotelService(HotelRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Hotel> updateHotel(UUID id, HotelAddress address) {
        return repository.fetch(id).map(hotel -> {
                    hotel.setAddress(address);
                    return repository.save(hotel);
                });
    }
}
