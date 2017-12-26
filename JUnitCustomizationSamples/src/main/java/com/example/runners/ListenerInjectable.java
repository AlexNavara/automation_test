package com.example.runners;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class ListenerInjectable extends Runner {

    private final Class<?> testClass;

    public ListenerInjectable(final Class<?> testClass)
    {
        this.testClass = testClass;
    }

    @Override
    public Description getDescription() {
        return Description.createSuiteDescription(testClass);
    }

    @Override
    public void run(RunNotifier notifier) {
        Listeners annotation = testClass.getAnnotation(Listeners.class);
        Collection<RunListener> addedListeners = new ArrayList<>();

        for (Class<? extends RunListener> listener : annotation.value()) {
            try {
                RunListener l = listener.newInstance();
                notifier.addListener(listener.newInstance());
                addedListeners.add(l);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

        for (Method method : testMethods()) {
            Description testDescription = Description.createTestDescription(testClass, method.getName());

            if (method.isAnnotationPresent(Ignore.class)) {
                notifier.fireTestIgnored(testDescription);
                continue;
            }

            notifier.fireTestStarted(testDescription);

            try {
                runTest(method);
            } catch (Throwable e) {
                notifier.fireTestFailure(new Failure(testDescription, e));
            } finally {
                notifier.fireTestFinished(testDescription);
            }
        }

        addedListeners.forEach(notifier::removeListener);
    }


    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Listeners {
        Class<? extends RunListener>[] value();
    }

    private Collection<Method> testMethods() {
        return Arrays.stream(testClass.getMethods())
                .filter(m -> m.isAnnotationPresent(Test.class))
                .collect(Collectors.toList());
    }

    private void runTest(final Method test) throws Throwable {
        Object instance = testClass.newInstance();
        try {
            test.invoke(instance);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }
}
