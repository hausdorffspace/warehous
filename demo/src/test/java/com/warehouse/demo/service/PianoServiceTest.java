package com.warehouse.demo.service;

import com.warehouse.demo.model.*;
import com.warehouse.demo.model.request.*;
import com.warehouse.demo.model.response.PianoResponse;
import com.warehouse.demo.repository.PianoRepository;
import com.warehouse.demo.utility.Mapper;
import com.warehouse.demo.utility.ModelChecker;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;


@SpringBootTest
class PianoServiceTest {

    @Autowired
    private PianoService pianoService;

    @MockBean
    private PianoRepository pianoRepository;

    @Test
    @DisplayName("Test savePiano Succes")
    void whenSavePianoShouldReturnOptionalPianoResponse() {
        //Setup mock repository
        Piano response = createPiano();

        doReturn(response).when(pianoRepository).save(any());

        //Execute the service call
        Optional<PianoResponse> returnedPiano = pianoService.save(createPianoRequest());

        //Assert the response
        Assertions.assertNotNull(returnedPiano,"The saved piano should not be null");
        Assertions.assertEquals(false,returnedPiano.get().getBorrowed());
    }

    @Test
    void testGetPianioByName() {
        //Setup mock repository
        Piano piano = createPiano();
        doReturn(piano).when(pianoRepository).getPianoByName(piano.getName());

        //Execute the service call
        Piano returnedPiano = pianoService.getPianioByName(piano.getName()).get();
        
        //assert the response

        Assertions.assertNotNull(returnedPiano,"returned piano should not be null");
        Assertions.assertEquals("testName",returnedPiano.getName());
    }


    @Test
    void testGetAllPianoByModel(){
        //setup mock repository
        Piano piano = createPiano();
        piano.setId(1L);
        Piano piano1 = createPiano();
        piano1.setId(2L);
        String model = "B";
        doReturn(Arrays.asList(piano,piano1)).when(pianoRepository).getAllPianoByModel(any());

        //Execute the service call
        List<Piano> response = pianoService.getAllPianoByModel(model).get();

        //Assert the response
        response.forEach(p-> Assertions.assertSame(ModelOfPiano.GRAND_PIANO_B_211,p.getModelOfPiano()));
        Assertions.assertEquals(2,response.size());

    }

    @Test
    void testGetAllPianoShouldReturnZeroElements(){
        //setup mock repository
        doReturn(Collections.emptyList()).when(pianoRepository).findAll();

        //execute the service call
        List<Piano> response = pianoService.getAllPiano().get();

        //assert the response
        Assertions.assertEquals(0,response.size());
    }

    @Test
    void testGetAllPiano(){
        Piano piano = createPiano();
        Piano piano1 = createPiano();
        doReturn(Arrays.asList(piano,piano1)).when(pianoRepository).findAll();
    }



    private PianoRequest createPianoRequest() {
        return PianoRequest.builder()
                .name("testName")
                .price(1)
                .weight(1)
                .dimension(DimensionRequest.builder()
                        .height(1)
                        .width(1)
                        .length(1)
                        .build())
                .modelOfPiano(ModelPianoRequest.B)
                .producer(ProducerRequest.builder()
                        .companyName("Stainway")
                        .build())
                .warehouse(WarehouseRequest.builder()
                        .description("description")
                        .location("Krakow")
                        .build())
                .build();
    }


    private Piano createPiano() {
        return Piano.builder()
                .id(1L)
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

}