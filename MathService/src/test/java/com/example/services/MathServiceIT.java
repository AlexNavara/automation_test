package com.example.services;

import com.example.dao.EquationDAOImpl;
import com.example.models.Equation;
import com.example.services.maths.Evaluator;
import com.example.services.parser.ExpressionParser;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class MathServiceIT {

    private static MathService mathService;

    private String input;
    private int expected;

    public MathServiceIT(String input, int expected) {
        this.input = input;
        this.expected = expected;
    }

    @Test
    public void shouldCalculateCorrectExpression() throws Exception {
        Equation actual = mathService.evaluate(input);
        assertEquals(expected, (int)actual.getResult());
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
        mathService = new MathService(new ExpressionParser(), new Evaluator(), new EquationDAOImpl());
    }

    @AfterClass
    public static void tearDownClass() {
        mathService = null;
    }
}
