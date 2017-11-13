package com.example.services.maths;

import com.example.models.Expression;
import com.example.models.Operation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MathServiceTest {

    private static IMathService mathService;

    @BeforeClass
    public static void setupClass() {
        mathService = new MathService();
    }

    @AfterClass
    public static void tearDownClass() {
        mathService = null;
    }

    @Test
    public void shouldAddTwoPositives() {
        int a = 1, b = 5, expected = 6;

        Expression expression = new Expression();
        expression.setFirstOperand(a);
        expression.setSecondOperand(b);
        expression.setOperation(Operation.ADD);

        int actual = mathService.evaluate(expression);

        assertEquals(expected, actual);
    }

    @Test
    public void shouldAddTwoNegatives() {
        int a = -8, b = -14, expected = -22;

        Expression expression = new Expression();
        expression.setFirstOperand(a);
        expression.setSecondOperand(b);
        expression.setOperation(Operation.ADD);

        int actual = mathService.evaluate(expression);

        assertEquals(expected, actual);
    }


}
