package com.example.samples.suites;

import com.example.samples.rules.RetryTestCountSampleTest;
import com.example.samples.runners.ListenerInjectionRunnerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        RetryTestCountSampleTest.class,
        ListenerInjectionRunnerTest.class
})
public class CustomSuite {
}
