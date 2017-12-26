package com.example.listeners;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

public class CustomListener extends RunListener {

    @Override
    public void testRunStarted(Description description) throws Exception {
        System.out.println("Started class " + description.getClassName());
    }

    @Override
    public void testRunFinished(Result result) throws Exception {
        System.out.println("Finished class");
        System.out.println("Total tests: " + result.getRunCount());
        System.out.println("Total failures: " + result.getFailureCount());
        System.out.println("Total ignored: " + result.getIgnoreCount());
        System.out.println("Run time: " + result.getRunTime());
    }

    @Override
    public void testFinished(Description description) throws Exception {
        System.out.println("Test finished: " + description.getMethodName());
    }

    @Override
    public void testIgnored(Description description) throws Exception {
        System.out.println("Test ignored: " + description.getMethodName());
    }

    @Override
    public void testStarted(Description description) throws Exception {
        System.out.println("Test started: " + description.getMethodName());
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
        System.out.println("Test failed: " + failure.getException().getMessage());
    }
}
