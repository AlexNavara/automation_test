package com.example.rules;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Method-level rule that retries test run up to maxRunsCount times in case of failure
 */
public class RetryTestCount implements TestRule
{
    private static final int DEFAULT_MAX_TRIES_COUNT = 1;
    private final int maxRunsCount;

    public RetryTestCount(final int maxRunsCount)
    {
        this.maxRunsCount = maxRunsCount > 0 ? maxRunsCount : DEFAULT_MAX_TRIES_COUNT;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                Throwable testError = null;
                int runsCounter = 0;
                while (runsCounter < maxRunsCount) {
                    try {
                        base.evaluate();
                        return;
                    } catch (Throwable e) {
                        testError = e;
                        runsCounter++;
                    }
                }
                throw new AssertionError(String.format("Failed after %d attempts", maxRunsCount), testError);
            }
        };
    }
}
