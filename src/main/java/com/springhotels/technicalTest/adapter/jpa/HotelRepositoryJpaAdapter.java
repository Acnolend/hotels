package com.springhotels.technicalTest.adapter.jpa;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<Hotel> fetchAll(Pageable pageable) {
        return jpaRepo.findAll(pageable)
                .map(mapper::toDomain);
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
    public Page<Hotel> fetchByCity(Pageable pageable, String city) {
        return jpaRepo.findByAddressCity(pageable, city)
                .map(mapper::toDomain);
    }


}
