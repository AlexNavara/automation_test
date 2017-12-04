package com.example.dao;

import com.example.models.Equation;

import java.util.List;

public interface EquationDAO {

    Equation insert(final Equation equation);

    void delete(final int id);

    void deleteAll();

    Equation findById(final int id);

    List<Equation> findAll();

}
