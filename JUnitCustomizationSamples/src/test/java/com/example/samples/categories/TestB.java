package com.example.samples.categories;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TestB {

    @Test
    @Category(Regression.class)
    public void regressionB() {}

    @Test
    @Category(Smoke.class)
    public void smokeB() {}

    @Test
    @Category({Regression.class, Smoke.class})
    public void both() {}

}
