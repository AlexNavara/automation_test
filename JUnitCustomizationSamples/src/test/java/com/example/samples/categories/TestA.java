package com.example.samples.categories;

import org.junit.Test;
import org.junit.experimental.categories.Category;

public class TestA {

    @Test
    @Category(Regression.class)
    public void regressionA() {}

    @Test
    @Category(Smoke.class)
    public void smokeA() {}

    @Test
    @Category({Regression.class, Smoke.class})
    public void both() {}

    @Test
    public void noCategory() {}

}
