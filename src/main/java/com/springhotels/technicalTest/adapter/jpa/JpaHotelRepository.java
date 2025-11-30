package com.springhotels.technicalTest.adapter.jpa;

import com.springhotels.technicalTest.adapter.jpa.entity.HotelJpaEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaHotelRepository extends JpaRepository<HotelJpaEntity, UUID> {

    List<HotelJpaEntity> findByAddressCity(String city);
}
