package com.example.samples.categories;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.IncludeCategory(Regression.class)
@Categories.ExcludeCategory(Smoke.class)
@Suite.SuiteClasses({
        TestA.class,
        TestB.class
})
public class TestSuite {
}
