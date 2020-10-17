package com.warehouse.demo.utility;

import com.warehouse.demo.service.EmailSenderService;
import org.springframework.stereotype.Component;

import java.util.TimerTask;


@Component
public class TimerTastImpl extends TimerTask {

    private String addressEmail;

    private EmailSenderService emailSenderService;

    public TimerTastImpl(String addressEmail) {
        this.addressEmail = addressEmail;
    }

    @Override
    public void run() {
        System.out.println("to jest ");
    }
}
