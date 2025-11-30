package com.springhotels.technicalTest.adapter.rest.controller;

import java.sql.SQLException;
import java.util.List;
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

import com.springhotels.technicalTest.adapter.rest.controller.request.AddressDTO;
import com.springhotels.technicalTest.adapter.rest.controller.request.HotelPostDTO;
import com.springhotels.technicalTest.adapter.rest.controller.request.HotelPutDTO;
import com.springhotels.technicalTest.adapter.rest.controller.response.HotelResponseDTO;
import com.springhotels.technicalTest.application.usecases.CreateHotelUseCase;
import com.springhotels.technicalTest.application.usecases.DeleteHotelUseCase;
import com.springhotels.technicalTest.application.usecases.ReadHotelUseCase;
import com.springhotels.technicalTest.application.usecases.UpdateHotelUseCase;
import com.springhotels.technicalTest.domain.entity.Hotel;
import com.springhotels.technicalTest.domain.enumerate.HotelStars;
import com.springhotels.technicalTest.domain.valueobject.HotelAddress;
import com.springhotels.technicalTest.domain.valueobject.HotelName;

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
    public ResponseEntity<List<HotelResponseDTO>> getHotels() {
        List<HotelResponseDTO> response = this.readHotelUseCase.getHotels().stream()
                .map(hotel -> new HotelResponseDTO(
                    hotel.getId().toString(), 
                    hotel.getName().getName(), 
                    hotel.getStars().getValue(), 
                    AddressDTO.fromAddress(hotel.getAddress())))
                .toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<HotelResponseDTO> getHotel(@PathVariable String id) {
        Hotel hotel = this.readHotelUseCase.getHotel(UUID.fromString(id));
        HotelResponseDTO response = new HotelResponseDTO(
            hotel.getId().toString(),
            hotel.getName().getName(),
            hotel.getStars().getValue(),
            AddressDTO.fromAddress(hotel.getAddress()));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("city/{city}")
    public ResponseEntity<List<HotelResponseDTO>> getHotelsByCity(@PathVariable String city) {
        List<HotelResponseDTO> response = this.readHotelUseCase.getHotelsByCity(city).stream()
                .map(hotel -> new HotelResponseDTO(
                    hotel.getId().toString(), 
                    hotel.getName().getName(), 
                    hotel.getStars().getValue(), 
                    AddressDTO.fromAddress(hotel.getAddress())))
                .toList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody HotelPostDTO hotelDTO) {
        AddressDTO addressDTO = hotelDTO.getAddress();
        Hotel createdHotel = this.createHotelUseCase.createHotel(
            new HotelName(hotelDTO.getName()),
            HotelStars.fromValue(hotelDTO.getStars()),
            new HotelAddress(addressDTO.getStreet(), addressDTO.getCity(), addressDTO.getCountry(), addressDTO.getPostalCode()));
        return new ResponseEntity<>(createdHotel, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Hotel> updateHotel(@PathVariable String id, @RequestBody HotelPutDTO hotelDTO) {   
        AddressDTO addressDTO = hotelDTO.getAddress();
        Hotel updatedHotel = this.updateHotelUseCase.updateHotel(
            UUID.fromString(id), 
            new HotelAddress(addressDTO.getStreet(), addressDTO.getCity(), addressDTO.getCountry(), addressDTO.getPostalCode()));
        return new ResponseEntity<>(updatedHotel, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteHotel(@PathVariable String id) throws SQLException {
        final boolean deleted = this.deleteHotelUseCase.deleteHotel(UUID.fromString(id));
        return new ResponseEntity<>(deleted, deleted ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
