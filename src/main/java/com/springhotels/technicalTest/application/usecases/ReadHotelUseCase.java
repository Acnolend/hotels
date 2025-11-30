package com.springhotels.technicalTest.application.usecases;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.springhotels.technicalTest.domain.entity.Hotel;

public interface ReadHotelUseCase {

    /**
     * This method return a hotel searched by ID.
     * @param id
     * @return a Hotel.
     */
    public Hotel getHotel(UUID id);
    /**
     * This method return all hotels.
     * @return list of hotels.
     */
    public Page<Hotel> getHotels(Pageable pageable);
    /**
     * This method return a list of hotels by a city.
     * @param city
     * @return
     */
    public Page<Hotel> getHotelsByCity(Pageable pageable, String city);
}
