package com.example.api;

import io.restassured.RestAssured;

public final class RestAssuredClientImplementation implements MathApiClient {
    @Override
    public String evaluateExpression(String expression) {
        return RestAssured.given()
                .baseUri("http://localhost:8080")
                .expect()
                .statusCode(200)
                .when()
                .get("/Calculator?" + expression.replaceAll(" ", ""))
                .asString();
    }
}
