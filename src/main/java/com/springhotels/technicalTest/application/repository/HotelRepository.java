package com.springhotels.technicalTest.application.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springhotels.technicalTest.domain.entity.Hotel;

public interface HotelRepository {
    
    public abstract boolean delete(UUID id);

    public abstract Page<Hotel> fetchAll(Pageable pageable);

    public abstract Hotel fetch(UUID id);

    public abstract Hotel save(Hotel hotel);

    public abstract Page<Hotel> fetchByCity(Pageable pageable, String city);
}
