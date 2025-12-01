package com.springhotels.technicalTest.adapter.rest.controller;

import java.sql.SQLException;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springhotels.technicalTest.adapter.rest.request.AddressDTO;
import com.springhotels.technicalTest.adapter.rest.request.HotelPostDTO;
import com.springhotels.technicalTest.adapter.rest.request.HotelPutDTO;
import com.springhotels.technicalTest.adapter.rest.response.HotelResponseDTO;
import com.springhotels.technicalTest.application.usecases.CreateHotelUseCase;
import com.springhotels.technicalTest.application.usecases.DeleteHotelUseCase;
import com.springhotels.technicalTest.application.usecases.ReadHotelUseCase;
import com.springhotels.technicalTest.application.usecases.UpdateHotelUseCase;
import com.springhotels.technicalTest.domain.entity.Hotel;
import com.springhotels.technicalTest.domain.enumerate.HotelStars;
import com.springhotels.technicalTest.domain.valueobject.HotelAddress;
import com.springhotels.technicalTest.domain.valueobject.HotelName;

import jakarta.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    
    private CreateHotelUseCase createHotelUseCase;
    private DeleteHotelUseCase deleteHotelUseCase;
    private ReadHotelUseCase readHotelUseCase;
    private UpdateHotelUseCase updateHotelUseCase;

    public HotelController(
        CreateHotelUseCase createHotelUseCase,
        DeleteHotelUseCase deleteHotelUseCase,
        ReadHotelUseCase readHotelUseCase,
        UpdateHotelUseCase updateHotelUseCase
    ) {
        this.createHotelUseCase = createHotelUseCase;
        this.deleteHotelUseCase = deleteHotelUseCase;
        this.readHotelUseCase = readHotelUseCase;
        this.updateHotelUseCase = updateHotelUseCase;
    }

    @GetMapping
    public ResponseEntity<Page<HotelResponseDTO>> getHotels(Pageable pageable) {
        Page<Hotel> page = this.readHotelUseCase.getHotels(pageable);
        Page<HotelResponseDTO> dtoPage = page.map(hotel ->
                new HotelResponseDTO(
                    hotel.getId().toString(),
                    hotel.getName().getName(),
                    hotel.getStars().getValue(),
                    AddressDTO.fromAddress(hotel.getAddress())
                )
        );
        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<HotelResponseDTO> getHotel(@PathVariable String id) {
        return this.readHotelUseCase.getHotel(UUID.fromString(id))
                .map(hotel -> { HotelResponseDTO response = new HotelResponseDTO(
                            hotel.getId().toString(),
                            hotel.getName().getName(),
                            hotel.getStars().getValue(),
                            AddressDTO.fromAddress(hotel.getAddress())
                    );
                    return new ResponseEntity<>(response, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("city/{city}")
    public ResponseEntity<Page<HotelResponseDTO>> getHotelsByCity(Pageable pageable, @PathVariable String city) {
        Page<Hotel> page = this.readHotelUseCase.getHotelsByCity(pageable, city);
        Page<HotelResponseDTO> response = page.map(hotel ->
                new HotelResponseDTO(
                        hotel.getId().toString(),
                        hotel.getName().getName(),
                        hotel.getStars().getValue(),
                        AddressDTO.fromAddress(hotel.getAddress())
                )
        );
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<HotelResponseDTO> createHotel(@Valid @RequestBody HotelPostDTO hotelDTO) {
        AddressDTO addressDTO = hotelDTO.getAddress();
        Hotel createdHotel = this.createHotelUseCase.createHotel(
            new HotelName(hotelDTO.getName()),
            HotelStars.fromValue(hotelDTO.getStars()),
            new HotelAddress(addressDTO.getStreet(), addressDTO.getCity(), addressDTO.getCountry(), addressDTO.getPostalCode()));

        HotelResponseDTO response = new HotelResponseDTO(
            createdHotel.getId().toString(),
            createdHotel.getName().getName(),
            createdHotel.getStars().getValue(),
            AddressDTO.fromAddress(createdHotel.getAddress())
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<HotelResponseDTO> updateHotel(@PathVariable String id, @Valid @RequestBody HotelPutDTO hotelDTO) {
        AddressDTO addressDTO = hotelDTO.getAddress();
        return this.updateHotelUseCase.updateHotel(
                        UUID.fromString(id),
                        new HotelAddress(addressDTO.getStreet(), addressDTO.getCity(), addressDTO.getCountry(), addressDTO.getPostalCode())
                ).map(updatedHotel -> { HotelResponseDTO response = new HotelResponseDTO(
                            updatedHotel.getId().toString(),
                            updatedHotel.getName().getName(),
                            updatedHotel.getStars().getValue(),
                            AddressDTO.fromAddress(updatedHotel.getAddress())
                    );
                    return new ResponseEntity<>(response, HttpStatus.OK);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteHotel(@PathVariable String id) throws SQLException {
        final boolean deleted = this.deleteHotelUseCase.deleteHotel(UUID.fromString(id));
        return new ResponseEntity<>(deleted, deleted ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
