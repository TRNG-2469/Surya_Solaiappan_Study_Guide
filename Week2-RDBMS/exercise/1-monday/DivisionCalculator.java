package com.cohort.exceptions;

public class DivisionCalculator {

    public static int add(String numbers) throws InvalidInputException {
        int result = 0;
        for (int number : parseNumbers(numbers)) {
            result += number;
        }
        return result;
    }

    public static int multiply(String numbers) throws InvalidInputException {
        int[] parsedNumbers = parseNumbers(numbers);
        if (parsedNumbers.length == 0) {
            return 0;
        }

        int result = 1;
        for (int number : parsedNumbers) {
            result *= number;
        }

        return result;
    }

    public static int divide(String numbers) throws InvalidInputException {
        int[] parsedNumbers = parseNumbers(numbers);
        if (parsedNumbers.length == 0) {
            return 0;
        }

        int result = parsedNumbers[0];
        for (int i = 1; i < parsedNumbers.length; i++) {
            if (parsedNumbers[i] == 0) {
                throw new InvalidInputException("Division by zero is mathematically undefined");
            }
            result /= parsedNumbers[i];
        }

        return result;
    }

    private static int[] parseNumbers(String numbers) throws InvalidInputException {
        if (numbers == null) {
            throw new InvalidInputException("Input argument cannot be null.");
        }

        if (numbers.trim().isEmpty()) {
            return new int[0];
        }

        String[] tokens = numbers.split(",", -1);
        int[] parsedNumbers = new int[tokens.length];

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i].trim();
            if (token.isEmpty()) {
                throw new InvalidInputException("Input cannot contain empty numbers.");
            }
            try {
                parsedNumbers[i] = Integer.parseInt(token);
            } catch (NumberFormatException e) {
                throw new InvalidInputException("Inputs must be valid integers. Parsing failed.");
            }
        }

        return parsedNumbers;
    }

}
