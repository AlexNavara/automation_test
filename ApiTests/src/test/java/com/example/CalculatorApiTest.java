package com.example;

import com.example.api.ApacheClientImplementation;
import com.example.api.JavaNetClientImplementation;
import com.example.api.MathApiClient;
import com.example.api.RestAssuredClientImplementation;
import org.junit.Assert;
import org.junit.Test;

public class CalculatorApiTest {

    private MathApiClient apiClient;

    @Test
    public void withJavaNetClient() {
        apiClient = new JavaNetClientImplementation();
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

    private void executeTest() {
        final String expected = "42";
        final String actual = apiClient.evaluateExpression("84/2");
        Assert.assertEquals(expected, actual);
    }

}
