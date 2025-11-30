package com.springhotels.technicalTest.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springhotels.technicalTest.application.repository.HotelRepository;
import com.springhotels.technicalTest.application.usecases.CreateHotelUseCase;
import com.springhotels.technicalTest.domain.entity.Hotel;
import com.springhotels.technicalTest.domain.enumerate.HotelStars;
import com.springhotels.technicalTest.domain.valueobject.HotelAddress;
import com.springhotels.technicalTest.domain.valueobject.HotelName;

@Service
public class CreateHotelService implements CreateHotelUseCase {

    @Autowired
    private HotelRepository repository;

    @Override
    public Hotel createHotel(HotelName name, HotelStars stars, HotelAddress address) {
        System.out.println("Nombre del hotel: " + name.toString());
        System.out.println("Estrellas del hotel: " + stars.toString());
        System.out.println("Dirección: " + address.toString());
        return this.repository.save(new Hotel(name, stars, address));
    }
}
