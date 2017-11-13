package com.example.services.maths.operations;

final class SubOperation implements BiOperandMathOperation {
    @Override
    public int evaluate(int a, int b) {
        return a - b;
    }
}
