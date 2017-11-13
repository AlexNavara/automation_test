package com.example.services.parser;

import com.example.models.Expression;
import com.example.models.Operation;
import com.example.models.ParseExpessionException;

public class ExpressionParser implements IExpressionParser {

    public Expression parse(final String expression) throws ParseExpessionException {
        Expression result = new Expression();
        try {
            String trimmed = expression.replaceAll(" ", "");
            int operationIndex = getOperationIndex(trimmed);
            String firstOperand = trimmed.substring(0, operationIndex);
            String secondOperand = trimmed.substring(operationIndex + 1, trimmed.length());
            String operation = getOperation(trimmed);

            result.setFirstOperand(Integer.valueOf(firstOperand));
            result.setSecondOperand(Integer.valueOf(secondOperand));
            switch (operation) {
                case "+":
                    result.setOperation(Operation.ADD);
                    break;
                case "-":
                    result.setOperation(Operation.SUB);
                    break;
                case "*":
                    result.setOperation(Operation.MUL);
                    break;
                case "/":
                    result.setOperation(Operation.DIV);
                    break;
                default:
                    throw new ParseExpessionException("Operation " + operation + " is not permitted");
            }
        } catch (Exception e) {
            throw new ParseExpessionException("Cannot parse " + expression, e);
        }

        return result;
    }

    private int getOperationIndex(final String expression) {
        String operation = getOperation(expression);

        if (expression.startsWith("-")) {
            return expression.indexOf(operation, 1);
        } else {
            return expression.indexOf(operation);
        }
    }

    private String getOperation(final String expression) {
        String operations = expression.replaceAll("\\d", "");

        if (operations.startsWith("-") && operations.length() > 1)
            return String.valueOf(operations.charAt(1));

        return String.valueOf(operations.charAt(0));
    }

}
