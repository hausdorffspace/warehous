package com.warehouse.demo.service;

import com.warehouse.demo.model.Piano;
import com.warehouse.demo.model.response.PianoResponse;
import com.warehouse.demo.repository.BankAccountRepository;
import com.warehouse.demo.repository.PianoRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
class PurshaseServiceTest {

    @Autowired
    PurshaseService purshaseService;

    @MockBean
    PianoRepository pianoRepository;

    @MockBean
    BankAccountRepository bankAccountRepository;

    @Test
    void testSellPianoShouldUpdateBankAccount(){
        String sku = "asdf";
        Integer price = 900000;
        //Setup mock repository

        doReturn(Piano.builder()
                .SKU(sku)
                .price(price)
                .build())
                .when(pianoRepository).getPianoBySKU(sku);

        doReturn(0).when(bankAccountRepository).getOne(any());
        doReturn(price).when(bankAccountRepository).updateBankAccount(price);

        //execute the service call
        Optional<PianoResponse> pianoResponse = purshaseService.sellPiano(sku);

        //assert the response
        assertNotNull(pianoResponse.get(),"retrived piano should not be null");
    }

}