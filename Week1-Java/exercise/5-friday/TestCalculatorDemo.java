package com.rev.demo;

import com.revature.demo.Calculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCalculatorDemo {

     static Calculator calculator = null;
    @BeforeAll
    public static void setUp(){
        calculator = new Calculator();
    }

    @Test
    public void addTest(){
        // AAA: Arrange, Act, Assert
        int a = 10;
        int b = 30;
        int expectedResult = 40;
        int actualResult = calculator.add(a,b);

        Assertions.assertEquals(actualResult,expectedResult);
    }

    @Test
    public void subtractTest(){
        int a = 30;
        int b = 20;
        int expectedResult = 10;
        int actualResult = calculator.subtract(30, 20);

        Assertions.assertEquals(actualResult, expectedResult);
    }

    @Test
    public void multiplyTest(){
        int a = 3;
        int b = 2;
        int expectedResult = 6;
        int actualResult = calculator.multiply(a,b);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void divideTest(){
        int a = 10;
        int b = 0;
        String expectedResult = "Undefined";
        String actualResult = calculator.divide(a,b).toString();
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void aboveBounds(){
        int a = 1;
        int b = Integer.MAX_VALUE;
        int expectedResult = Integer.MIN_VALUE;
        int actualResult = calculator.add(a,b);
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void belowBounds(){
        int b = 1;
        int a = Integer.MIN_VALUE;
        int expectedResult = Integer.MAX_VALUE;
        int actualResult = calculator.subtract(a,b);
        Assertions.assertEquals(expectedResult, actualResult);
    }


    // edge cases: INTEGER_MIN_VALUE - 1
    // INTEGER_MAX_VALUE +1

}
