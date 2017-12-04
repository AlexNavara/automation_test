package com.example.services.maths;

import com.example.models.Expression;
import com.example.services.maths.operations.BiOperationsChooser;

public class Evaluator implements IEvaluator {

    public int evaluate(final Expression expression) {
        return BiOperationsChooser.getOperationExecutor(expression.getOperation())
                .evaluate(expression.getFirstOperand(), expression.getSecondOperand());
    }

}
