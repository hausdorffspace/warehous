package com.warehouse.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.warehouse.demo.model.*;
import com.warehouse.demo.model.request.PianoRequest;
import com.warehouse.demo.model.response.DimensionResponse;
import com.warehouse.demo.model.response.PianoResponse;
import com.warehouse.demo.model.response.ProducerResponse;
import com.warehouse.demo.model.response.WarehouseResponse;
import com.warehouse.demo.service.PianoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PianoController.class)
class PianoControllerTest {


    @MockBean
    PianoService pianoService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void test() {
        try {
            this.mockMvc.perform(get("/api/test"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].test",is("test")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void savePianoShouldReturnPianoFromService() {

        PianoResponse returnerPiano = PianoResponse.builder()
                .SKU("QWERTY")
                .name("qwerty")
                .id(1L)
                .modelOfPiano(ModelOfPiano.GRAND_PIANO_C_227)
                .weight(400)
                .price(100000)
                .producer(ProducerResponse.builder()
                        .companyName("Stainway")
                        .build())
                .borrowed(false)
                .dimension(DimensionResponse.builder()
                        .height(1)
                        .width(1)
                        .Length(1)
                        .build())
                .warehouse(WarehouseResponse.builder()
                        .description("desc")
                        .location("Krakow")
                        .build())
                .build();


        when(pianoService.save(any(PianoRequest.class)))
                .thenReturn(Optional.ofNullable(Piano.builder()
                        .SKU("QWERTY")
                        .name("qwerty")
                        .id(1L)
                        .modelOfPiano(ModelOfPiano.GRAND_PIANO_C_227)
                        .weight(400)
                        .price(100000)
                        .producer(Producer.builder()
                                .companyName("Stainway")
                                .build())
                        .borrowed(false)
                        .dimension(Dimension.builder()
                                .height(1)
                                .width(1)
                                .length(1)
                                .build())
                        .warehouse(Warehouse.builder()
                                .description("desc")
                                .location("Krakow")
                                .build())
                        .build()));

        try {
            this.mockMvc.perform(post("/save")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(returnerPiano)))
                    .andDo(print())
                    .andExpect(status().isCreated())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$[0].SKU", is("QWERTY")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAllPianoTest(){
        Piano piano = createPiano();
        piano.setId(1L);
        Piano piano1 = createPiano();
        piano1.setId(2L);
        doReturn(Optional.of(Arrays.asList(piano,piano1))).when(pianoService).getAllPiano();

        try {
            mockMvc.perform(get("/pianos"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$",hasSize(2)))
                    .andExpect(jsonPath("$[0].name",is("testName")))
                    .andExpect(jsonPath("$[1].name", is("testName")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getPianoByName() {
        when(pianoService.getPianioByName(any()))
                .thenReturn(Optional.ofNullable(
                        Piano.builder()
                                .SKU("SKU")
                                .name("stainway")
                                .build()));
        try {
            mockMvc.perform(get("/piano/{name}","stainway"))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$[0].sku",is("SKU")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getAllPianoByModel() {
        when(pianoService.getAllPianoByModel("P"));
    }

    @Test
    void updatePianoWithSku() {
    }

    @Test
    void deletePianoWithSku() {
    }


    private Piano createPiano() {
        return Piano.builder()
                .name("testName")
                .price(1)
                .SKU("QWERTY")
                .weight(1)
                .borrowed(false)
                .dimension(Dimension.builder()
                        .length(1)
                        .height(1)
                        .width(1)
                        .build())
                .modelOfPiano(ModelOfPiano.GRAND_PIANO_B_211)
                .producer(Producer.builder()
                        .companyName("Stainway")
                        .build())
                .warehouse(Warehouse.builder()
                        .location("Krakow")
                        .description("description")
                        .build())
                .build();
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}