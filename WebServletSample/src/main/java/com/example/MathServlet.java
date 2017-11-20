package com.example;

import com.example.models.ParseExpressionException;
import com.example.services.Calculator;
import com.example.services.maths.MathService;
import com.example.services.parser.ExpressionParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MathServlet extends HttpServlet {

    private Calculator calculator = new Calculator(
            new ExpressionParser(),
            new MathService()
    );


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String expression = req.getQueryString();

        try {
            resp.setContentType("text/plain");
            resp.getOutputStream().print(calculator.evaluate(expression));
        } catch (ParseExpressionException e) {
            resp.sendError(400, e.getMessage());
        }

    }
}
