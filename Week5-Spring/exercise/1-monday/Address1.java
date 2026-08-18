package com.example;

import org.springframework.stereotype.Component;

@Component
public class Address1 {
    private String name;
    private String city;
    private int pincode;

    public Address1() {}

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getPincode() {
        return pincode;
    }
}