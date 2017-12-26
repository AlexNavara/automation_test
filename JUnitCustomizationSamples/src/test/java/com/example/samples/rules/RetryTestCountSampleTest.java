package com.example.samples.rules;

import com.example.rules.RetryTestCount;
import org.junit.Rule;
import org.junit.Test;

public class RetryTestCountSampleTest {

    @Rule
    public RetryTestCount testCount = new RetryTestCount(10);

    @Test
    public void unstableTest() {
        if (Math.random() < 0.5) {
            throw new RuntimeException("Ooops...");
        }
    }

}
