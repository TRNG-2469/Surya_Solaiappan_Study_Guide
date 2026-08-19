package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Car2 {
    String model;
    double price;
    Engine2 engine2;

    public Car2(@Value("${car.model:Toyota}") String model,
                @Value("${car.price:15000}") double price, Engine2 engine2){
        this.model=model;
        this.price=price;
        this.engine2=engine2;
    }

    @Override
    public String toString() {
        return "Car2{" +
                "model='" + model + '\'' +
                ", price=" + price +
                ", engine2=" + engine2 +
                '}';
    }
}
