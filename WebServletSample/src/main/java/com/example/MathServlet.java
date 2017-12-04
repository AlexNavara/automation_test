package com.example;

import com.example.dao.EquationDAOImpl;
import com.example.models.Equation;
import com.example.models.ParseExpressionException;
import com.example.services.MathService;
import com.example.services.maths.Evaluator;
import com.example.services.parser.ExpressionParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class MathServlet extends HttpServlet {

    private MathService mathService = new MathService(
            new ExpressionParser(),
            new Evaluator(),
            new EquationDAOImpl()
    );

    private static final Gson GSON = new GsonBuilder()
            .serializeNulls()
            .create();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (Reader in = new InputStreamReader(req.getInputStream())){
            Equation input = GSON.fromJson(in, Equation.class);
            try {
                resp.setContentType("application/json");
                resp.getOutputStream().print(GSON.toJson(mathService.evaluate(input.getExpression())));
            } catch (ParseExpressionException e) {
                resp.sendError(400, e.getMessage());
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (ServletOutputStream out = resp.getOutputStream()){
            resp.setContentType("application/json");
            out.print(GSON.toJson(mathService.getHistory()));
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        mathService.clearHistory();
    }
}
