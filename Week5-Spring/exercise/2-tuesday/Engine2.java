package com.example.demo;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

@Component
public class Engine2 {
    private String type;
    private int horsepower;

    public Engine2(@Value("${engine.type:Petrol}") String type,
                   @Value("${engine.horsepower:120}") int horsepower) {
        this.type = type;
        this.horsepower = horsepower;
    }

    public String getType() {
        return type;
    }

    public int getHorsepower() {
        return horsepower;
    }

    @Override
    public String toString() {
        return "Engine2{" +
                "type='" + type + '\'' +
                ", horsepower=" + horsepower +
                '}';
    }
}
