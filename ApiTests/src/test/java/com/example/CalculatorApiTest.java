package com.example;

import com.example.api.ApacheClientImplementation;
import com.example.api.JavaNetClientImplementation;
import com.example.api.MathApiClient;
import com.example.api.RestAssuredClientImplementation;
import com.example.models.Equation;
import org.junit.Assert;
import org.junit.Test;

public class CalculatorApiTest {

    private MathApiClient apiClient;

    @Test
    public void withJavaNetClient() {
//        apiClient = new JavaNetClientImplementation();
        executeTest();
    }

    @Test
    public void withApacheClient() {
//        apiClient = new ApacheClientImplementation();
        executeTest();
    }

    @Test
    public void withRestAssuredClient() {
        apiClient = new RestAssuredClientImplementation();
        executeTest();
    }

    private void executeTest() {
        final Integer expected = 42;
        final Equation equation = new Equation();
        equation.setExpression("84/2");
        final Integer actual = apiClient.postEquation(equation).getResult();
        Assert.assertEquals(expected, actual);
    }

}
