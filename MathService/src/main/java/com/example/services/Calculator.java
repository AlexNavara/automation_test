package com.example.services;

import com.example.models.Expression;
import com.example.models.ParseExpressionException;
import com.example.services.maths.IMathService;
import com.example.services.parser.IExpressionParser;

public class Calculator {

    private IExpressionParser parser;
    private IMathService mathService;

    public Calculator(final IExpressionParser parser, final IMathService mathService) {
        this.parser = parser;
        this.mathService = mathService;
    }

    public int evaluate(final String expression) throws ParseExpressionException
    {
        Expression exp = parser.parse(expression);
        return mathService.evaluate(exp);
    }

}
