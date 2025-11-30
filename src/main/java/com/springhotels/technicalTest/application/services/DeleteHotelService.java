package com.springhotels.technicalTest.application.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springhotels.technicalTest.application.repository.HotelRepository;
import com.springhotels.technicalTest.application.usecases.DeleteHotelUseCase;

@Service
public class DeleteHotelService implements DeleteHotelUseCase {

    @Autowired
    private HotelRepository repository;

    @Override
    public boolean deleteHotel(UUID id) {
        return this.repository.delete(id);
    }
}
