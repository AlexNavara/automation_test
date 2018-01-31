package com.example;

import com.example.api.ApacheClientImplementation;
import com.example.api.MathApiClient;
import com.example.api.RestAssuredClientImplementation;
import com.example.models.Equation;
import com.example.rules.BenchmarkRule;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

public class CalculatorApiTest {

    @Rule
    public BenchmarkRule benchmarkRule = new BenchmarkRule();

    private MathApiClient apiClient = new ApacheClientImplementation();

    @Ignore
    @Test
    public void withJavaNetClient() {
//        apiClient = new JavaNetClientImplementation();
        executeTest();
    }

    @Test
    public void withApacheClient() {
        apiClient = new ApacheClientImplementation();
        executeTest();
    }

    @Test
    public void withRestAssuredClient() {
        apiClient = new RestAssuredClientImplementation();
        executeTest();
    }

    @Test
    public void withRestGet() {
        apiClient = new RestAssuredClientImplementation();
        apiClient.getAllEquations();
    }

    @Test
    public void withApacheGet() {
        apiClient = new ApacheClientImplementation();
        apiClient.getAllEquations();
    }

    private void executeTest() {
        final Integer expected = 42;
        final Equation equation = new Equation();
        equation.setExpression("84/2");
        final Integer actual = apiClient.postEquation(equation).getResult();
        Assert.assertEquals(expected, actual);

        System.out.println(apiClient.getAllEquations().size());
    }

}
