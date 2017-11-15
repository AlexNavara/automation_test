package com.example.services.parser;

import com.example.models.Expression;
import com.example.models.ParseExpressionException;

public interface IExpressionParser {

    Expression parse(final String strExpression) throws ParseExpressionException;

}
