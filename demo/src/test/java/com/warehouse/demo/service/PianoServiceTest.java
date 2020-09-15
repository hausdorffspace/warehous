package com.warehouse.demo.service;

import com.warehouse.demo.model.*;
import com.warehouse.demo.model.request.*;
import com.warehouse.demo.model.response.PianoResponse;
import com.warehouse.demo.repository.PianoRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
class PianoServiceTest {

    @Mock
    PianoRepository pianoRepository;

    @InjectMocks
    PianoService pianoService;


    @Test
    void whenSavePianoShouldReturnOptionalPianoResponse() {
        //given
        when(pianoRepository.save(createPiano())).thenReturn(createPiano());
        //when
        Optional<PianoResponse> save = pianoService.save(createPianoRequest());
        //then
        assertThat(save.get().getName()).isSameAs(createPiano().getName());

    }

    @Test()
    void getPianioByName() {
        //given

        String name = "testName";
        //when
        Piano piano1 = pianoService.getPianioByName(name).get();
        //then

    }


    private PianoRequest createPianoRequest() {
        return PianoRequest.builder()
                .name("testName")
                .price(1)
                .sku("QWERTY")
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