package com.springhotels.technicalTest.adapter.jpa;

import com.springhotels.technicalTest.adapter.jpa.entity.HotelJpaEntity;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaHotelRepository extends JpaRepository<HotelJpaEntity, UUID> {

    Page<HotelJpaEntity> findByAddressCity(Pageable pageable, String city);
}
