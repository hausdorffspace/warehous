package com.warehouse.demo.service;

import com.warehouse.demo.model.*;
import com.warehouse.demo.model.request.*;
import com.warehouse.demo.repository.PianoRepository;
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
import static org.mockito.ArgumentMatchers.eq;
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
        Optional<Piano> returnedPiano = pianoService.save(createPianoRequest());

        //Assert the response
        Assertions.assertNotNull(returnedPiano, "The saved piano should not be null");
        Assertions.assertEquals(false, returnedPiano.get().getBorrowed());
    }

    @Test
    void testGetPianioByName() {
        //Setup mock repository
        Piano piano = createPiano();
        doReturn(piano).when(pianoRepository).getPianoByName(piano.getName());

        //Execute the service call
        Piano returnedPiano = pianoService.getPianioByName(piano.getName()).get();

        //assert the response

        Assertions.assertNotNull(returnedPiano, "returned piano should not be null");
        Assertions.assertEquals("testName", returnedPiano.getName());
    }


    @Test
    void testGetAllPianoByModel() {
        //setup mock repository
        Piano piano = createPiano();
        piano.setId(1L);
        Piano piano1 = createPiano();
        piano1.setId(2L);
        String model = "B";
        doReturn(Arrays.asList(piano, piano1)).when(pianoRepository).getAllPianoByModel(any());

        //Execute the service call
        List<Piano> response = pianoService.getAllPianoByModel(model).get();

        //Assert the response
        response.forEach(p -> Assertions.assertSame(ModelOfPiano.GRAND_PIANO_B_211, p.getModelOfPiano()));
        Assertions.assertEquals(2, response.size());

    }

    @Test
    void testGetAllPianoShouldReturnZeroElements() {
        //setup mock repository
        doReturn(Collections.emptyList()).when(pianoRepository).findAll();

        //execute the service call
        List<Piano> response = pianoService.getAllPiano().get();

        //assert the response
        Assertions.assertEquals(0, response.size());
    }


    //TODO
    @Test
    void testGetAllPiano() {
        //setup mock repository
        Piano piano = createPiano();
        Piano piano1 = createPiano();
        doReturn(Arrays.asList(piano, piano1)).when(pianoRepository).findAll();

        //execute the service call
        Optional<List<Piano>> allPiano = pianoService.getAllPiano();
        List<Piano> pianos = allPiano.get();

        //assert response
        Assertions.assertEquals(2, pianos.size());
        Assertions.assertEquals(1, pianos.get(0).getPrice());
        Assertions.assertEquals(1, pianos.get(1).getPrice());
    }


    //Lukas help
    @Test
    void updatePianoWithSKUTest() {
        //setup mock repository
        Integer updatePrice = 999;
        Integer isUpdated = 1;
        Piano updatedPiano = UpdatedPiano();
        doReturn(isUpdated).when(pianoRepository).updatePianoPriceWithSku(any(), eq(updatePrice));
        doReturn(updatedPiano).when(pianoRepository).getPianoBySKU(any());

        //execute the service call
        Optional<Piano> optionalPiano = pianoService.updatePianoPriceWithSku(any(), eq(updatePrice));
        Piano piano = optionalPiano.get();

        //assert response
        Assertions.assertNotEquals(1, piano.getPrice());
        Assertions.assertEquals(999,piano.getPrice());
    }


    @Test
    void deletePianoWithSkuTest() {

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
                .modelOfPiano(ModelOfPiano.GRAND_PIANO_B_211)
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


    private Piano UpdatedPiano() {
        return Piano.builder()
                .id(1L)
                .name("testName")
                .price(999)
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