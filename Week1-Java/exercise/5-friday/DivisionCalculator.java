package com.cohort.exceptions;

public class DivisionCalculator {


    public static int divide(String numeratorStr, String denominatorStr) throws InvalidInputException{
        int result = 0;
        if(numeratorStr == null || denominatorStr == null || numeratorStr == "" || denominatorStr == ""){
            throw new InvalidInputException("Input arguments cannot be null or empty.");
        }

        try {
            int numerator = Integer.parseInt(numeratorStr);
            int denominator = Integer.parseInt(denominatorStr);
            if(denominator == 0){
                throw new InvalidInputException("Division by zero is mathematically undefined");
            }
            result = numerator / denominator;
        } catch (NumberFormatException e){
            throw new InvalidInputException("Inputs must be valid integers. Parsing failed.");
        }
        return result;
    }

    public static void main(String[] args){
        try {
           System.out.println(divide("100", "5"));
        } catch(InvalidInputException e){
            System.out.println(e.getMessage());
        } finally {
            System.out.println("[CALCULATOR] Execution cycle complete.");
        }


        try {
            System.out.println(divide("100", "0"));
        } catch(InvalidInputException e){
        System.out.println(e.getMessage());
        } finally {
            System.out.println("[CALCULATOR] Execution cycle complete.");
        }


        try{
            System.out.println(divide("abc", "5"));
        } catch(InvalidInputException e){
            System.out.println(e.getMessage());
        } finally {
            System.out.println("[CALCULATOR] Execution cycle complete.");
        }

        try {
            System.out.println(divide(null, "5"));
        } catch(InvalidInputException e){
        System.out.println(e.getMessage());
        } finally {
            System.out.println("[CALCULATOR] Execution cycle complete.");
        }
    }

}
