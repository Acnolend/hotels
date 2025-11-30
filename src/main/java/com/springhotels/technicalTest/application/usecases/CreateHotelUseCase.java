package com.springhotels.technicalTest.application.usecases;

import com.springhotels.technicalTest.domain.entity.Hotel;
import com.springhotels.technicalTest.domain.enumerate.HotelStars;
import com.springhotels.technicalTest.domain.valueobject.HotelAddress;
import com.springhotels.technicalTest.domain.valueobject.HotelName;

public interface CreateHotelUseCase {

    /**
     * This method create a hotel.
     * @param name
     * @param stars
     * @param address
     * @return a Hotel.
     */
    public Hotel createHotel(HotelName name, HotelStars stars, HotelAddress address);
}
