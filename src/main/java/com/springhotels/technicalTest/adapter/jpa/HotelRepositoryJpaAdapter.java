package com.springhotels.technicalTest.adapter.jpa;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.springhotels.technicalTest.adapter.jpa.mapper.HotelJpaMapper;
import com.springhotels.technicalTest.application.repository.HotelRepository;
import com.springhotels.technicalTest.domain.entity.Hotel;

@Repository
public class HotelRepositoryJpaAdapter implements HotelRepository {

    private final JpaHotelRepository jpaRepo;
    private final HotelJpaMapper mapper;

    public HotelRepositoryJpaAdapter(JpaHotelRepository jpaRepo, HotelJpaMapper mapper) {
        this.jpaRepo = jpaRepo;
        this.mapper = mapper;
    }

    @Override
    public boolean delete(UUID id) {
        if (!jpaRepo.existsById(id)) {
            return false;
        }
        jpaRepo.deleteById(id);
        return true;
    }

    @Override
    public List<Hotel> fetchAll() {
        return jpaRepo.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Hotel fetch(UUID id) {
        return jpaRepo.findById(id)
                .map(mapper::toDomain)
                .orElse(null);
    }

    @Override
    public Hotel save(Hotel hotel) {
        var entity = mapper.toJpa(hotel);
        var saved = jpaRepo.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public List<Hotel> fetchByCity(String city) {
        return jpaRepo.findByAddressCity(city).stream()
                .map(mapper::toDomain)
                .toList();
    }
}
