package com.example.utils;

import com.example.models.Equation;
import com.example.models.Operation;

import java.util.Random;

public class RandomEquationGenerator {

    private static final Random random = new Random(System.currentTimeMillis());

    public static Equation generate() {
        int a = random.nextInt();
        int b = random.nextInt();
        Operation op = Operation.values()[Math.abs(random.nextInt() % Operation.values().length)];

        Equation e = new Equation();
        e.setExpression(a + op.toString() + b);
        e.setResult(random.nextInt());

        return e;
    }

}
