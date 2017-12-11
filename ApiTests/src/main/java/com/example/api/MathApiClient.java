package com.example.api;

import com.example.models.Equation;

import java.util.List;

public interface MathApiClient {

    Equation postEquation(final Equation equation);

    Equation getEquationById(final int id);

    List<Equation> getAllEquations();

    void deleteEquation(final int id);

}
