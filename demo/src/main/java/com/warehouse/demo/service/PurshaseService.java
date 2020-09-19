package com.warehouse.demo.service;


import com.warehouse.demo.model.BankAccount;
import com.warehouse.demo.model.ModelOfPiano;
import com.warehouse.demo.model.Piano;
import com.warehouse.demo.model.response.PianoResponse;
import com.warehouse.demo.repository.BankAccountRepository;
import com.warehouse.demo.repository.PianoRepository;
import com.warehouse.demo.utility.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PurshaseService {

    private PianoRepository pianoRepository;

    private BankAccountRepository bankAccountRepository;

    @Autowired
    public PurshaseService(PianoRepository pianoRepository, BankAccountRepository bankAccountRepository) {
        this.pianoRepository = pianoRepository;
        this.bankAccountRepository = bankAccountRepository;
    }

    public final Optional<PianoResponse> sellPiano(String sku) {
        Piano pianoBySKU = pianoRepository.getPianoBySKU(sku);
        if (Optional.ofNullable(pianoBySKU).isPresent()){
            Integer accountBalance = bankAccountRepository.getOne(1L).getAccountBallance();
            bankAccountRepository.updateBankAccount(accountBalance + pianoBySKU.getPrice());
            pianoRepository.deletePianoWithSku(sku);
            return Optional.ofNullable(Mapper.mapPianoToPianoResponse(pianoBySKU));
        } else {
            return Optional.empty();
        }
    }
}
