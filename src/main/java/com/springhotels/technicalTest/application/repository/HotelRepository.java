package com.springhotels.technicalTest.application.repository;

import java.util.List;
import java.util.UUID;

import com.springhotels.technicalTest.domain.entity.Hotel;

public interface HotelRepository {
    
    public abstract boolean delete(UUID id);

    public abstract List<Hotel> fetchAll();

    public abstract Hotel fetch(UUID id);

    public abstract Hotel save(Hotel hotel);

    public abstract List<Hotel> fetchByCity(String city);
}
