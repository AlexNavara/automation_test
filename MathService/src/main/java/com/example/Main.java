package com.example;

import com.example.services.Calculator;
import com.example.services.maths.MathService;
import com.example.services.parser.ExpressionParser;

public class Main {

    public static void main(String[] args) throws Exception {
        Calculator calculator = new Calculator(new ExpressionParser(), new MathService());

        int result = calculator.evaluate("5 + 5");

        System.out.println(result);
    }

}
