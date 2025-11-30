package com.springhotels.technicalTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springhotels.technicalTest.adapter.rest.controller.request.AddressDTO;
import com.springhotels.technicalTest.adapter.rest.controller.request.HotelPostDTO;
import com.springhotels.technicalTest.application.usecases.CreateHotelUseCase;
import com.springhotels.technicalTest.application.usecases.DeleteHotelUseCase;
import com.springhotels.technicalTest.application.usecases.ReadHotelUseCase;
import com.springhotels.technicalTest.application.usecases.UpdateHotelUseCase;
import com.springhotels.technicalTest.domain.entity.Hotel;
import com.springhotels.technicalTest.domain.enumerate.HotelStars;
import com.springhotels.technicalTest.domain.valueobject.HotelAddress;
import com.springhotels.technicalTest.domain.valueobject.HotelName;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean private CreateHotelUseCase createHotelUseCase;
    @MockBean private DeleteHotelUseCase deleteHotelUseCase;
    @MockBean private ReadHotelUseCase readHotelUseCase;
    @MockBean private UpdateHotelUseCase updateHotelUseCase;

    private Hotel mockHotel() {
        return new Hotel(
                UUID.randomUUID(),
                new HotelName("Hotel Test"),
                HotelStars.FOUR_STARS,
                new HotelAddress("Street", "Madrid", "Spain", "28000")
        );
    }

    // ---------------------------------------------------------------------
    // GET /hotels   (solo usuario autenticado)
    // ---------------------------------------------------------------------
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testPagination() throws Exception {
        Page<Hotel> page = new PageImpl<>(List.of(mockHotel()));
        Mockito.when(readHotelUseCase.getHotels(Mockito.any()))
                .thenReturn(page);
        mockMvc.perform(get("/hotels?page=0&size=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Hotel Test"));
    }

    // ---------------------------------------------------------------------
    // GET /hotels/id/{id}
    // ---------------------------------------------------------------------
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testGetHotelById() throws Exception {
        Hotel hotel = mockHotel();
        Mockito.when(readHotelUseCase.getHotel(hotel.getId()))
                .thenReturn(hotel);

        mockMvc.perform(get("/hotels/id/" + hotel.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Hotel Test"));
    }

    // ---------------------------------------------------------------------
    // GET /hotels/city/{city}
    // ---------------------------------------------------------------------
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testGetHotelsByCity() throws Exception {

        Hotel hotel = mockHotel();

        Page<Hotel> page = new PageImpl<>(List.of(hotel));

        Mockito.when(readHotelUseCase.getHotelsByCity(Mockito.any(), Mockito.eq("Madrid")))
                .thenReturn(page);

        mockMvc.perform(get("/hotels/city/Madrid"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].address.city").value("Madrid"))
                .andExpect(jsonPath("$.totalElements").value(1))
                .andExpect(jsonPath("$.totalPages").value(1));
    }

    // ---------------------------------------------------------------------
    // POST /hotels   (solo usuario autenticado)
    // ---------------------------------------------------------------------
    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testCreateHotel() throws Exception {
        Hotel hotel = mockHotel();

        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setStreet("Street");
        addressDTO.setCity("Madrid");
        addressDTO.setCountry("Spain");
        addressDTO.setPostalCode("28000");

        HotelPostDTO input = new HotelPostDTO("Hotel Test", 4, addressDTO);

        Mockito.when(createHotelUseCase.createHotel(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(hotel);

        mockMvc.perform(post("/hotels")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Hotel Test"));
    }

    // ---------------------------------------------------------------------
    // DELETE /hotels/{id} - SOLO ADMIN
    // ---------------------------------------------------------------------
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    void testDeleteHotelAsAdmin() throws Exception {
        Mockito.when(deleteHotelUseCase.deleteHotel(Mockito.any()))
                .thenReturn(true);

        mockMvc.perform(delete("/hotels/" + UUID.randomUUID()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    void testDeleteHotelAsUserForbidden() throws Exception {
        mockMvc.perform(delete("/hotels/" + UUID.randomUUID()))
                .andExpect(status().isForbidden());
    }
}
