package com.example;

import com.example.dao.EquationDAO;
import com.example.dao.EquationDAOImpl;
import com.example.models.Equation;

public class Main {

    public static void main(String[] args) {
        EquationDAO dao = new EquationDAOImpl();

        Equation eq = new Equation();
        eq.setResult(5);
        eq.setExpression("2+3");

        Equation savedEquation = dao.insert(eq);
    }

}
