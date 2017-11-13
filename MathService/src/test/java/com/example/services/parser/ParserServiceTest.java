package com.example.services.parser;

import com.example.models.Expression;
import com.example.models.Operation;
import com.example.services.parser.ExpressionParser;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParserServiceTest {

    private static IExpressionParser parser;

    private String input;
    private int firstOperand;
    private int secondOperand;
    private Operation operation;

    public ParserServiceTest(String input, int firstOperand, int secondOperand, Operation operation) {
        this.input = input;
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.operation = operation;
    }

    @Test
    public void shouldParseExpression() throws Exception {
        Expression expression = parser.parse(input);

        assertEquals(firstOperand, expression.getFirstOperand());
        assertEquals(secondOperand, expression.getSecondOperand());
        assertEquals(operation, expression.getOperation());
    }

    @Parameterized.Parameters(name = "Expression: {0}")
    public static Iterable<Object[]> inputs() {
        return Arrays.asList(new Object[][] {
                {"100 +8", 100, 8, Operation.ADD},
                {"-5- 0", -5, 0, Operation.SUB},
                {"74 * -23", 74, -23, Operation.MUL},
                {"-92 / -685", -92, -685, Operation.DIV}
        });
    }

    @BeforeClass
    public static void setupClass() {
        parser = new ExpressionParser();
    }

    @AfterClass
    public static void tearDownClass() {
        parser = null;
    }

}
