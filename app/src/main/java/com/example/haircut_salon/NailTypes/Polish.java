package com.example.haircut_salon.NailTypes;

public class Polish implements INailOptions {

    @Override
    public int getPrice() {
        return 30;
    }

    @Override
    public String getName() {
        return "Nail Polish";
    }
}

