package com.example.nailit.NailTypes;

public class Gel implements INailOptions {

    @Override
    public int getPrice() {
        return 50;
    }

    @Override
    public String getName() {
        return "Gel Nail Polish";
    }
}
