package com.example.haircut_salon.NailTypes;

public class PolishRemover implements  INailOptions {

    @Override
    public int getPrice() {
        return 10;
    }

    @Override
    public String getName() {
        return "Nail Polish Remover";
    }
}