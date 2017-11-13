package com.example.services;

import com.example.services.maths.MathService;
import com.example.services.parser.ExpressionParser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class CalculatorIT {

    private static Calculator calculator;

    private String input;
    private int expected;

    public CalculatorIT(String input, int expected) {
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void shouldCalculateCorrectExpression() throws Exception {
        int actual = calculator.evaluate(input);
        assertEquals(expected, actual);
    }

    @Parameterized.Parameters(name = "Input: {0}")
    public static Iterable<Object[]> inputs() {
        return Arrays.asList(new Object[][] {
                {"12 + 8", 20},
                {"-9-0", -9},
                {"123 * -1", -123},
                {"-42/ 7", -6}
        });
    }

    @BeforeClass
    public static void setupClass() {
        calculator = new Calculator(new ExpressionParser(), new MathService());
    }

    @AfterClass
    public static void teadDownClass() {
        calculator = null;
    }
}
