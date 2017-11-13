package com.example.models;

public final class Expression {

    private int firstOperand;
    private int secondOperand;
    private Operation operation;

    public void setFirstOperand(int firstOperand) {
        this.firstOperand = firstOperand;
    }

    public void setSecondOperand(int secondOperand) {
        this.secondOperand = secondOperand;
    }

    public int getFirstOperand() {
        return firstOperand;
    }

    public int getSecondOperand() {
        return secondOperand;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
