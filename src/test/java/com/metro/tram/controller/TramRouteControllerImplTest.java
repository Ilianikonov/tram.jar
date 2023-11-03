package com.metro.tram.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.metro.tram.controller.request.TramRouteRequest;
import com.metro.tram.controller.response.TramRouteResponse;
import com.metro.tram.dto.TramRouteDto;
import com.metro.tram.service.TramRouteService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TramRouteControllerImplTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TramRouteService tramRouteService;
    @Autowired
    private ObjectMapper objectMapper;

    private static final String RECEIVING_AND_CREATING_TRAM_ROUTES = "/trams";
    private static final String GETTING_DELETE_UPDATED_TRAM_ROUTE = "/trams/{id}";

    @Test
    @Order(1)
    void getTrams() throws Exception {
        TramRouteDto tramRoute0 = createTramRoute("wfawf","wafawf");
        TramRouteDto tramRoute1 = createTramRoute("gnggegng", "32353rf");
        TramRouteDto tramRoute2 = createTramRoute("gegeng", "3233f");
        TramRouteDto tramRoute3 = createTramRoute("geggng", "32eg2rf");
        long id0 = tramRouteService.createTram(tramRoute0);
        long id1 = tramRouteService.createTram(tramRoute1);
        long id2 = tramRouteService.createTram(tramRoute2);
        long id3 = tramRouteService.createTram(tramRoute3);
        mockMvc.perform(get(RECEIVING_AND_CREATING_TRAM_ROUTES))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder((int)id0, (int)id1, (int)id2, (int)id3)))
                .andExpect(jsonPath("$[*].name", containsInAnyOrder(tramRoute0.getName(), tramRoute1.getName(), tramRoute2.getName(), tramRoute3.getName())))
                .andExpect(jsonPath("$[*].code", containsInAnyOrder(tramRoute0.getCode(), tramRoute1.getCode(), tramRoute2.getCode(), tramRoute3.getCode())));
    }

    @Test
    void getTramsById() throws Exception {
        TramRouteDto tramRouteDto = createTramRoute("gnауg", "3rуаf");
        long id = tramRouteService.createTram(tramRouteDto);
        mockMvc.perform(get(GETTING_DELETE_UPDATED_TRAM_ROUTE,id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value(tramRouteDto.getName()))
                .andExpect(jsonPath("$.code").value(tramRouteDto.getCode()));
    }

    @Test
    void createTrams() throws Exception {
        TramRouteRequest tramRouteRequest = new TramRouteRequest();
        tramRouteRequest.setName("afefeaf");
        tramRouteRequest.setCode("efaeaf332");
        String jsonTramRouteRequest = mockMvc.perform(post(RECEIVING_AND_CREATING_TRAM_ROUTES)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tramRouteRequest)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        long id = objectMapper.readValue(jsonTramRouteRequest,Long.class);
        mockMvc.perform(get(GETTING_DELETE_UPDATED_TRAM_ROUTE,id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(tramRouteRequest.getName()))
                .andExpect(jsonPath("$.code").value(tramRouteRequest.getCode()));
    }

    @Test
    void deleteTramById() throws Exception {
        TramRouteDto tramRouteDto = createTramRoute("gnwfwуg", "3qwfаf");
        long id = tramRouteService.createTram(tramRouteDto);
        mockMvc.perform(get(GETTING_DELETE_UPDATED_TRAM_ROUTE,id))
                .andExpect(status().isOk());
        mockMvc.perform(delete(GETTING_DELETE_UPDATED_TRAM_ROUTE,id))
                .andExpect(status().isOk());
        mockMvc.perform(get(GETTING_DELETE_UPDATED_TRAM_ROUTE,id))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateTramRoute() throws Exception {
        TramRouteDto tramRouteDto = createTramRoute("weweewfуg", "wefew12f");
        long id = tramRouteService.createTram(tramRouteDto);
        TramRouteRequest tramRouteRequest = new TramRouteRequest();
        tramRouteRequest.setName("efwegwe");
        String jsonTramRouteRequest = mockMvc.perform(patch(GETTING_DELETE_UPDATED_TRAM_ROUTE,id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tramRouteRequest)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        TramRouteResponse tramRouteResponse = objectMapper.readValue(jsonTramRouteRequest,TramRouteResponse.class);
        Assertions.assertEquals(tramRouteResponse.getId(),id);
        Assertions.assertEquals(tramRouteResponse.getName(),tramRouteRequest.getName());
        Assertions.assertEquals(tramRouteResponse.getCode(),tramRouteDto.getCode());

    }

    private TramRouteDto createTramRoute(String name, String code){
        TramRouteDto tramRouteDto = new TramRouteDto();
        tramRouteDto.setCode(code);
        tramRouteDto.setName(name);
        return tramRouteDto;
    }

    private TramRouteDto convertToTramRoute (TramRouteResponse tramRouteResponse){
        TramRouteDto tramRouteDto = new TramRouteDto();
        tramRouteDto.setId(tramRouteResponse.getId());
        tramRouteDto.setName(tramRouteResponse.getName());
        tramRouteDto.setCode(tramRouteResponse.getCode());
        return tramRouteDto;
    }
}