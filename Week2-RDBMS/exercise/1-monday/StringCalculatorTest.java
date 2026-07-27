package com.cohort.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StringCalculatorTest {
    

        StringCalculator stringCalculator = new StringCalculator();

        @Test
        public void testAddSimpleNumbers() {
            // Arrange

            // Act
            int result = stringCalculator.add("5,3");

            // Assert
            assertEquals(8, result);
        }

        @Test
        public void multiplyNumbers() {
            // Arrange

            // Act
            int result = stringCalculator.multiply("2,3,4");

            // Assert
            assertEquals(24, result);

            int result2 = stringCalculator.multiply("");

            // Assert
            assertEquals(0, result2);
        }

        @Test
        public void divideNumbers() {
            // Arrange

            // Act
            int result = stringCalculator.divide("10,2");

            // Assert
            assertEquals(5, result);


            // Assert
            assertThrows(IllegalArgumentException.class, () -> stringCalculator.divide("10,0"));
        }
}

