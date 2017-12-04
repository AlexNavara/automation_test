package com.example.dao;

import com.example.DbConnectionSource;
import com.example.models.Equation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquationDAOImpl implements EquationDAO {

    private static final String TABLE_NAME = "Equation";
    private static final String INSERT = "INSERT INTO " + TABLE_NAME + " (expression, result) VALUES (?, ?)";
    private static final String REMOVE = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
    private static final String REMOVE_ALL = "DELETE FROM " + TABLE_NAME + " WHERE 1=1";
    private static final String SELECT_BY_ID = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
    private static final String SELECT_ALL = "SELECT * FROM " + TABLE_NAME;

    @Override
    public Equation insert(Equation equation) {

        Equation result = null;

        try (
                Connection connection = DbConnectionSource.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)
                )
        {
            statement.setString(1, equation.getExpression());
            statement.setObject(2, equation.getResult());
            statement.execute();

            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                resultSet.next();

                result = new Equation();
                result.setExpression(equation.getExpression());
                result.setResult(equation.getResult());
                result.setId(resultSet.getInt(1));
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public void delete(int id) {
        try (
                Connection connection = DbConnectionSource.INSTANCE.getConnection();
                PreparedStatement statement = connection.prepareStatement(REMOVE)
                )
        {
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        try(PreparedStatement statement = DbConnectionSource.INSTANCE.getConnection().prepareStatement(REMOVE_ALL))
        {
            statement.execute();
        }
        catch (SQLException e)
        {
            for (Throwable er : e)
                er.printStackTrace();
        }
    }

    @Override
    public Equation findById(int id) {

        Equation result = null;
        try(PreparedStatement statement = DbConnectionSource.INSTANCE.getConnection().prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()){
                if (resultSet.next()) {
                    result = new Equation();
                    result.setId(resultSet.getInt("id"));
                    result.setExpression(resultSet.getString("expression"));
                    result.setResult((Integer) resultSet.getObject("result"));
                }
            }
        }
        catch (SQLException e)
        {
            for (Throwable er : e)
                er.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Equation> findAll() {
        List<Equation> result = new ArrayList<>();
        try (ResultSet resultSet = DbConnectionSource.INSTANCE
                .getConnection()
                .prepareStatement(SELECT_ALL)
                .executeQuery())
        {
            while (resultSet.next()) {
                Equation equation = new Equation();
                equation.setResult((Integer) resultSet.getObject("result"));
                equation.setExpression(resultSet.getString("expression"));
                equation.setId(resultSet.getInt("id"));
                result.add(equation);
            }
        }
        catch (SQLException e)
        {
            for (Throwable er : e)
                er.printStackTrace();
        }
        return result;
    }
}
