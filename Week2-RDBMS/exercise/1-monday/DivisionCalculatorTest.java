package com.cohort.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DivisionCalculatorTest {

    @Test
    void addWithEmptyStringReturnsZero() {
        assertEquals(0, DivisionCalculator.add(""));
    }

    @Test
    void multiplyWithEmptyStringReturnsZero() {
        assertEquals(0, DivisionCalculator.multiply(""));
    }

    @Test
    void addWithNullInputThrowsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> DivisionCalculator.add(null));
    }

    @Test
    void multiplyWithNegativeValuesReturnsPositiveProduct() {
        assertEquals(24, DivisionCalculator.multiply("-2,-3,4"));
    }

    @Test
    void addWithSpacesAroundNumbersReturnsSum() {
        assertEquals(15, DivisionCalculator.add(" 4, 5 , 6 "));
    }

    @Test
    void divideWithSpacesAroundNumbersReturnsQuotient() {
        assertEquals(10, DivisionCalculator.divide(" 100 , 5 , 2 "));
    }

    @Test
    void addWithTrailingCommaThrowsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> DivisionCalculator.add("1,2,"));
    }

    @Test
    void divideByZeroThrowsInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> DivisionCalculator.divide("100,0"));
    }

    @Test
    void divideWithNegativeValuesReturnsExpectedQuotient() {
        assertEquals(10, DivisionCalculator.divide("-100,-5,2"));
    }
}
