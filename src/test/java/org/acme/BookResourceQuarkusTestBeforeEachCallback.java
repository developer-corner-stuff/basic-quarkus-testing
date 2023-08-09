package org.acme;

import io.quarkus.test.junit.callback.QuarkusTestBeforeEachCallback;
import io.quarkus.test.junit.callback.QuarkusTestMethodContext;

import java.lang.annotation.Annotation;
import java.util.Arrays;

// Other Callbacks which can be overriden and used to enrich tests
//  io.quarkus.test.junit.callback.QuarkusTestBeforeClassCallback
//
//  io.quarkus.test.junit.callback.QuarkusTestAfterConstructCallback
//
//  io.quarkus.test.junit.callback.QuarkusTestBeforeEachCallback
//
//  io.quarkus.test.junit.callback.QuarkusTestBeforeTestExecutionCallback
//
//  io.quarkus.test.junit.callback.QuarkusTestAfterTestExecutionCallback
//
//  io.quarkus.test.junit.callback.QuarkusTestAfterEachCallback
//
//  Property quarkus.test.enable-callbacks-for-integration-tests needs to be set to true

public class BookResourceQuarkusTestBeforeEachCallback implements QuarkusTestBeforeEachCallback {

    @Override
    public void beforeEach(QuarkusTestMethodContext context) {
        System.out.println("Executing " + context.getTestMethod());
        System.out.println("Test Method Annotations:");
        Annotation[] annotations = context.getTestInstance().getClass().getAnnotations();
        Arrays.stream(annotations)
                .forEach(System.out::println);
    }
}
