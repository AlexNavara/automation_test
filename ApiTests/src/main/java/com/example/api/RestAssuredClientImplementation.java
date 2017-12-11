package com.example.api;

import com.example.config.ConfigProvider;
import com.example.models.Equation;
import io.restassured.RestAssured;

import java.util.List;

public final class RestAssuredClientImplementation implements MathApiClient {

    @Override
    public Equation postEquation(Equation equation) {
        return RestAssured.given()
                .body(equation)
                .expect()
                .statusCode(200)
                .when()
                .post(ConfigProvider.INSTANCE.getServerUrl() + "/Calculator")
                .as(Equation.class);
    }

    @Override
    public Equation getEquationById(int id) {
        return RestAssured.given()
                .expect()
                .statusCode(200)
                .when()
                .get(ConfigProvider.INSTANCE.getServerUrl() + "/Calculator/{xxx}", id)
                .as(Equation.class);
    }

    @Override
    public List<Equation> getAllEquations() {
        return RestAssured.given()
                .expect()
                .statusCode(200)
                .when()
                .get(ConfigProvider.INSTANCE.getServerUrl() + "/Calculator")
                .as(List.class);
    }

    @Override
    public void deleteEquation(int id) {
        RestAssured.given()
                .expect()
                .statusCode(200)
                .when()
                .delete(ConfigProvider.INSTANCE.getServerUrl() + "/Calculator/{id}", id);
    }
}
