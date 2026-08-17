package com.example;

public class Address {
    private String city;
    private String name;
    private int pincode;

    public void setCity(String city) {
        this.city = city;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public int getPincode() {
        return pincode;
    }
}
