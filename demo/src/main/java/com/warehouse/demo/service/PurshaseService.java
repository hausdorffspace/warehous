package com.warehouse.demo.service;


import com.warehouse.demo.model.BorrowedPiano;
import com.warehouse.demo.model.Customer;
import com.warehouse.demo.model.Piano;
import com.warehouse.demo.model.SoldPiano;
import com.warehouse.demo.model.response.PianoResponse;
import com.warehouse.demo.repository.*;
import com.warehouse.demo.utility.Mapper;
import com.warehouse.demo.utility.TimerTastImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.Objects;
import java.util.Optional;
import java.util.Timer;

@Service
public class PurshaseService {

    private PianoRepository pianoRepository;

    private BankAccountRepository bankAccountRepository;

    private BorrowedPianoRepository borrowedPianoRepository;

    private CustomerRepository customerRepository;

    private SoldPianoRepository soldPianoRepository;

    @Autowired
    public PurshaseService(PianoRepository pianoRepository,
                           BankAccountRepository bankAccountRepository,
                           CustomerRepository customerRepository,
                           BorrowedPianoRepository borrowedPianoRepository,
                           SoldPianoRepository soldPianoRepository) {
        this.pianoRepository = pianoRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.customerRepository = customerRepository;
        this.borrowedPianoRepository = borrowedPianoRepository;
        this.soldPianoRepository = soldPianoRepository;
    }


    //customer ?  how to fetch the metadata about customer?
    public final Optional<Piano> sellPiano(String sku) {
        Optional<Piano> optionalPianoBySku = Optional.ofNullable(pianoRepository.getPianoBySkuWhichIsNotBorrowed(sku));  //get the piano which isn't borrowed
        if (optionalPianoBySku.isPresent()) {   //check if the piano is avaliable
            Piano pianoBySKU = optionalPianoBySku.get();
            Integer accountBalance = bankAccountRepository.getOne(1L).getAccountBallance();   //get tha account balance
            bankAccountRepository.updateBankAccount(accountBalance + pianoBySKU.getPrice());   //update bank account
            soldPianoRepository.save(SoldPiano.builder()
                    .modelOfPiano(pianoBySKU.getModelOfPiano())
                    .name(pianoBySKU.getName())
                    .price(pianoBySKU.getPrice())
                    .producer(pianoBySKU.getProducer())
                    .SKU(pianoBySKU.getSKU())
                    .warehouse(pianoBySKU.getWarehouse())
                    .weight(pianoBySKU.getWeight())
                    .buyer(Customer.builder().build())
                    .build());
            pianoRepository.deletePianoWithSku(sku);       //deleted bought piano    here is the error
            return Optional.ofNullable(pianoBySKU);
        } else {
            return Optional.empty();
        }
    }


    /*
     * the price for 1 month is equals 1% of basic price piano
     * */
    public Optional<Piano> borrowPianoWithSKUForThePeriod(String sku, Long period, String addressEmail) {

        //condition that confirm email activation link
        if (true) {
            Integer isUpdate = pianoRepository.updatePianoBorrowedWithSkuIfBorrowedIsFalse(sku);
            if (isUpdate == 1) {
                Piano borrowedPiano = pianoRepository.getPianoBySKU(sku);
                borrowedPianoRepository.save(BorrowedPiano.builder()
                        .borrowedPiano(borrowedPiano)
                        .priceForOneMonth(calculateMonthlyRent(borrowedPiano.getPrice())) //todo
                        .priceForTheEntireRentalPeriod(calculatePriceForEntirePeriod(period, calculateMonthlyRent(borrowedPiano.getPrice())))
                        .build());
                Timer timer = new Timer();
                timer.schedule(new TimerTastImpl(addressEmail), 10000);  //it will work after 10[s]

                return Optional.ofNullable(borrowedPiano);
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    private Double calculatePriceForEntirePeriod(Long period, Double monthlyFee) {
        return Math.toIntExact(period) * monthlyFee;
    }

    private Double calculateMonthlyRent(Integer priceOfThePiano) {
        return priceOfThePiano * 0.01;
    }
}
