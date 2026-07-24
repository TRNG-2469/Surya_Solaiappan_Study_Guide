package com.revature.demo;

public class Calculator {

    public int add(int a, int b){
        return a + b;
    }

    public int multiply(int a, int b){
        return a * b;
    }

    public int subtract(int a, int b){
        return a - b;
    }

    public Object divide(int a, int b){
        if(b==0) return "Undefined";
        return a / b;
    }


}
