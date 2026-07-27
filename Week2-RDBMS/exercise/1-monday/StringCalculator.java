package com.cohort.exceptions;

public class StringCalculator {

    public int add(String input) {
        if (input == null || input.trim().isEmpty()) {
            return 0;
        }
        String[] tokens = input.split(",");
        int sum = 0;
        for (String token : tokens) {
            sum += Integer.parseInt(token.trim());
        }
        return sum;
    }


    public int multiply(String numbers)  {
        if (numbers.length() == 0 || numbers == null) {
            return 0;
        }
        String[] tokens = numbers.split(",");
        int result = 1;
        for (String token  : tokens) {
            result *= Integer.parseInt(token.trim());
        }

        return result;
    }

    public int divide(String numbers)  {
        if (numbers.length() == 0 || numbers == null) {
            return 0;
        }
        String[] tokens = numbers.split(",");
        int result = Integer.parseInt(tokens[0].trim());
        for (int i = 1; i < tokens.length; i++) {
            int divisor = Integer.parseInt(tokens[i].trim());
            if(divisor!=0) result /= divisor;
            else{
                throw new IllegalArgumentException("Division by zero is undefined");
            }
        }

        return result;
    }
}