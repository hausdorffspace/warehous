package com.warehouse.demo.utility;

import org.springframework.stereotype.Component;

import java.util.TimerTask;


@Component
public class TimerTastImpl extends TimerTask {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("to jest ");
    }
}
