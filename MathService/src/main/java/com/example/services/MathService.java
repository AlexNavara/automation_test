package com.example.services;

import com.example.dao.EquationDAO;
import com.example.models.Equation;
import com.example.models.Expression;
import com.example.models.ParseExpressionException;
import com.example.services.maths.IEvaluator;
import com.example.services.parser.IExpressionParser;

import java.util.List;

public class MathService {

    private IExpressionParser parser;
    private IEvaluator mathService;
    private EquationDAO equationDAO;

    public MathService(final IExpressionParser parser, final IEvaluator mathService, final EquationDAO equationDAO) {
        this.parser = parser;
        this.mathService = mathService;
        this.equationDAO = equationDAO;
    }

    public Equation evaluate(final String expression) throws ParseExpressionException
    {
        Equation equation = new Equation();
        equation.setExpression(expression);

        try {
            Expression exp = parser.parse(expression);
            int result = mathService.evaluate(exp);

            equation.setResult(result);
        } catch (ParseExpressionException e) {
            equationDAO.insert(equation);
            throw e;
        }

        return equationDAO.insert(equation);
    }

    public void clearHistory()
    {
        equationDAO.deleteAll();
    }

    public List<Equation> getHistory()
    {
        return equationDAO.findAll();
    }

}
