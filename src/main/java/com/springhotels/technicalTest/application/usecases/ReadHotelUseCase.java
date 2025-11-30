package com.springhotels.technicalTest.application.usecases;

import java.util.List;
import java.util.UUID;

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
    public List<Hotel> getHotels();
    /**
     * This method return a list of hotels by a city.
     * @param city
     * @return
     */
    public List<Hotel> getHotelsByCity(String city);
}
