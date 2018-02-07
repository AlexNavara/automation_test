package com.example.samples.runners;

import com.example.listeners.CustomListener;
import com.example.runners.ListenerInjectable;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(ListenerInjectable.class)
@ListenerInjectable.Listeners(
        CustomListener.class
)
public class ListenerInjectionRunnerTest {

    @Test
    public void passingTest() {
        Assert.assertTrue(true);
    }

    @Ignore
    @Test
    public void ignoredTest() {

    }

    @Test
    public void failingTest() {
        Assert.assertTrue(true);
    }

}
