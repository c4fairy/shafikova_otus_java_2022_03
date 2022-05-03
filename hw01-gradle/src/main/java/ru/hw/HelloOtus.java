package ru.hw;

import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.List;

/**
 *
 * To start the application:
 * ./gradlew build
 * java -jar .//hw01-gradle/build/libs/gradleHelloWorld-0.1.jar
 *
 * To unzip the jar:
 * unzip -l .//hw01-gradle/build/libs//hw01-gradle.jar
 * unzip -l .//hw01-gradle/build/libs/gradleHelloWorld-0.1.jar
 *
 */

public class HelloOtus {
    public static void main(String[] args) {
        List<Integer> values = Lists.newArrayList(1, null, 3, 4, 5, null, 7);
        Iterable<Integer> filtered = Iterables.filter(values,
                Predicates.notNull());
        for (Integer i : filtered) {
            System.out.println(i);
        }
    }
}