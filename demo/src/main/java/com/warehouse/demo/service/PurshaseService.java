package com.warehouse.demo.service;


import com.warehouse.demo.model.BorrowedPiano;
import com.warehouse.demo.model.Piano;
import com.warehouse.demo.model.response.PianoResponse;
import com.warehouse.demo.repository.BankAccountRepository;
import com.warehouse.demo.repository.BorrowedPianoRepository;
import com.warehouse.demo.repository.PianoRepository;
import com.warehouse.demo.repository.RentierRepository;
import com.warehouse.demo.utility.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class PurshaseService {

    private PianoRepository pianoRepository;

    private BankAccountRepository bankAccountRepository;

    private BorrowedPianoRepository borrowedPianoRepository;

    private RentierRepository rentierRepository;

    @Autowired
    public PurshaseService(PianoRepository pianoRepository,
                           BankAccountRepository bankAccountRepository,
                           RentierRepository rentierRepository,
                           BorrowedPianoRepository borrowedPianoRepository) {
        this.pianoRepository = pianoRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.rentierRepository = rentierRepository;
        this.borrowedPianoRepository = borrowedPianoRepository;
    }

    public final Optional<PianoResponse> sellPiano(String sku) {
        Piano pianoBySKU = pianoRepository.getPianoBySKU(sku);
        if (Optional.ofNullable(pianoBySKU).isPresent()) {
            Integer accountBalance = bankAccountRepository.getOne(1L).getAccountBallance();
            bankAccountRepository.updateBankAccount(accountBalance + pianoBySKU.getPrice());
            pianoRepository.deletePianoWithSku(sku);
            return Optional.ofNullable(Mapper.mapPianoToPianoResponse(pianoBySKU));
        } else {
            return Optional.empty();
        }
    }


    /*
     * the price for 1 month is equals 1% of basic price piano
     * */
    public void borrowPianoWithSKUForThePeriod(String sku, Period period) {
        Piano pianoBySkuWhichIsNotBorrowed = pianoRepository.getPianoBySkuWhichIsNotBorrowed(sku);
        if (Objects.nonNull(pianoBySkuWhichIsNotBorrowed)) {
            Piano borrowedPiano = pianoRepository.updatePianoWithSku(sku, Boolean.TRUE);
            Timer timer = new Timer();
            borrowedPianoRepository.save(BorrowedPiano.builder()
                    .borrowedPiano(borrowedPiano)
                    .priceForOneDay(calculateDailyRent(borrowedPiano.getPrice()))
                    .priceForTheEntireRentalPeriod(calculatePriceForEntirePeriod(period))
                    .build()
            );
        }
/*
        Timer timer = new Timer();
        timer.*/
    }

    private Integer calculatePriceForEntirePeriod(Period period) {
        return null;
    }

    private Double calculateDailyRent(Integer borrowedPianoPrice) {
        return borrowedPianoPrice * 0.01;
    }
}
