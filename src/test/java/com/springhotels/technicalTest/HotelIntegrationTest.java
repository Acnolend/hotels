package com.springhotels.technicalTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springhotels.technicalTest.adapter.jpa.JpaHotelRepository;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class HotelIntegrationTest {

    @Autowired MockMvc mockMvc;
    @Autowired JpaHotelRepository hotelRepo;

    @Test
    @WithMockUser(username="admin", roles={"ADMIN"})
    void createHotel_then_fetchById() throws Exception {

        String response = mockMvc.perform(post("/hotels")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "name":"Hotel Roma",
                        "stars":4,
                        "address":{
                            "street":"Via Roma",
                            "city":"Roma",
                            "country":"Italia",
                            "postalCode":"00100"
                        }
                    }
                """))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        String id = new ObjectMapper().readTree(response).get("id").asText();

        mockMvc.perform(get("/hotels/id/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Hotel Roma"))
                .andExpect(jsonPath("$.stars").value(4))
                .andExpect(jsonPath("$.address.city").value("Roma"));
    }

    @Test
    @WithMockUser(username="user", roles={"USER"})
    void listHotels_withPagination() throws Exception {

        hotelRepo.save(TestUtils.jpaHotel("AA"));
        hotelRepo.save(TestUtils.jpaHotel("BB"));
        hotelRepo.save(TestUtils.jpaHotel("CC"));

        mockMvc.perform(get("/hotels?page=0&size=2&sort=name,asc"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("$.content[0].name").value("AA"));
    }

    @Test
    @WithMockUser(username="user", roles={"USER"})
    void deleteHotel_forbidden_forUser() throws Exception {

        UUID id = hotelRepo.save(TestUtils.jpaHotel("Test")).getId();

        mockMvc.perform(delete("/hotels/" + id))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username="admin", roles={"ADMIN"})
    void deleteHotel_allowed_forAdmin() throws Exception {

        UUID id = hotelRepo.save(TestUtils.jpaHotel("Test")).getId();

        mockMvc.perform(delete("/hotels/" + id))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="admin", roles={"ADMIN"})
    void createHotel_invalid_blankName_returns400() throws Exception {

        mockMvc.perform(post("/hotels")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "name":"",
                        "stars":5,
                        "address":{
                            "street":"Via",
                            "city":"Roma",
                            "country":"Italia",
                            "postalCode":"00100"
                        }
                    }
                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(username="admin", roles={"ADMIN"})
    void createHotel_invalid_starsOutOfRange_returns400() throws Exception {

        mockMvc.perform(post("/hotels")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                        "name":"Hotel x",
                        "stars":7,
                        "address":{
                            "street":"Via",
                            "city":"Roma",
                            "country":"Italia",
                            "postalCode":"00100"
                        }
                    }
                """))
                .andExpect(status().isBadRequest());
    }
}
