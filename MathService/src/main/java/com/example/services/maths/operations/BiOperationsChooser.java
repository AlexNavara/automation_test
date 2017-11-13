package com.example.services.maths.operations;

import com.example.models.Operation;

public final class BiOperationsChooser {

    private static final AddOperation addOperation = new AddOperation();
    private static final SubOperation subOperation = new SubOperation();
    private static final MulOperation mulOperation = new MulOperation();
    private static final DivOperation divOperation = new DivOperation();

    public static BiOperandMathOperation getOperationExecutor(final Operation operation) {
        switch (operation) {
            case ADD:
                return addOperation;
            case SUB:
                return subOperation;
            case DIV:
                return divOperation;
            case MUL:
                return mulOperation;
            default:
                throw new RuntimeException("Fatal error: unknown operation");
        }
    }

}
