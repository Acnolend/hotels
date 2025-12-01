package com.springhotels.technicalTest.application.usecases;

import java.util.Optional;
import java.util.UUID;

import com.springhotels.technicalTest.domain.entity.Hotel;
import com.springhotels.technicalTest.domain.valueobject.HotelAddress;

public interface UpdateHotelUseCase {

    /**
     * 
     * @param id
     * @param address
     */
    public Optional<Hotel> updateHotel(UUID id, HotelAddress address);
}
