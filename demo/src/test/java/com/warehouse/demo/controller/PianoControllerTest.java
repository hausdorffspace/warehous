package com.warehouse.demo.controller;

import com.warehouse.demo.model.request.PianoRequest;
import com.warehouse.demo.model.response.PianoResponse;
import com.warehouse.demo.service.PianoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;


import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
                    /*.andExpect(content().json(
                            "{" + "\n" + "\"name\": \"test\"" + "\n" +
                                    "}"

                    ));*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*@Test
    void savePianoShouldReturnPianoFromService() {
        when(pianoService.save(any(PianoRequest.class)))
                .thenReturn(Optional.ofNullable(PianoResponse.builder().build()));

        try {
            this.mockMvc.perform(get("/save"))
                    .andDo(print())
                    .andDo()
                    .andExpect(status().isOk())
                    .andExpect(content().json());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    @Test
    void getPianoByName() {
    }

    @Test
    void getAllPianoByModel() {
    }

    @Test
    void updatePianoWithSku() {
    }

    @Test
    void deletePianoWithSku() {
    }
}