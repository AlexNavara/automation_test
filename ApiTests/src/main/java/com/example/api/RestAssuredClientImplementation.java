package com.example.api;

import com.example.config.ConfigProvider;
import io.restassured.RestAssured;

public final class RestAssuredClientImplementation implements MathApiClient {
    @Override
    public String evaluateExpression(String expression) {
        return RestAssured.given()
                .log().ifValidationFails()
                .urlEncodingEnabled(false)
                .baseUri(ConfigProvider.INSTANCE.getServerUrl())
                .expect()
                .statusCode(200)
                .when()
                .get("/Calculator?" + expression.replaceAll(" ", ""))
                .asString();
    }
}
