package com.example.samples.categories;

import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(Regression.class)
public class RegressionOnlyTests {
    @Test
    public void regressionOne() {}

    @Test
    public void regressionTwo() {}
}
