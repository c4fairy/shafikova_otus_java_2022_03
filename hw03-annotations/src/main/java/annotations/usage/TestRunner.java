package annotations.usage;

import annotations.After;
import annotations.Before;
import annotations.Test;
import annotations.dict.TestAnnotation;
import annotations.util.ReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestRunner {
    public static void testRun(String className) throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        Method testMethods[] = clazz.getDeclaredMethods();
        Object object = ReflectionHelper.instantiate(clazz);
        Map<TestAnnotation, List<Method>> annotationMethodMap = getTestAnnotations(testMethods);
        int a = processBefore(object, annotationMethodMap);
        int b = processTest(object, annotationMethodMap);
        int c = processAfter(object, annotationMethodMap);
        int allA = annotationMethodMap.get(TestAnnotation.BEFORE).size();
        int allB = annotationMethodMap.get(TestAnnotation.TEST).size();
        int allC = annotationMethodMap.get(TestAnnotation.AFTER).size();
        System.out.println("All Before = " + allA);
        System.out.println("Success Before = " + a);
        System.out.println("Failed Before = " + (allA - a));
        System.out.println("========================================");
        System.out.println("All Test = " + allB);
        System.out.println("Success Test = " + b);
        System.out.println("Failed Test = " + (allB - b));
        System.out.println("========================================");
        System.out.println("All After = " + allC);
        System.out.println("Success After = " + c);
        System.out.println("Failed After = " + (allC - c));

    }

    private static Map<TestAnnotation, List<Method>> getTestAnnotations(Method[] testMethods) {
        Map<TestAnnotation, List<Method>> annotationMethodMap = new HashMap<>();
        List<Method> methodsBefore = new ArrayList<>();
        List<Method> methodsTest = new ArrayList<>();
        List<Method> methodsAfter = new ArrayList<>();
        for (int i = 0; i < testMethods.length; i++) {
            if (testMethods[i].isAnnotationPresent(Before.class)) {
                methodsBefore.add(testMethods[i]);
                annotationMethodMap.put(TestAnnotation.BEFORE, methodsBefore);
            }
        }
        for (int i = 0; i < testMethods.length; i++) {
            if (testMethods[i].isAnnotationPresent(Test.class)) {
                methodsTest.add(testMethods[i]);
                annotationMethodMap.put(TestAnnotation.TEST, methodsTest);
            }
        }
        for (int i = 0; i < testMethods.length; i++) {
            if (testMethods[i].isAnnotationPresent(After.class)) {
                methodsAfter.add(testMethods[i]);
                annotationMethodMap.put(TestAnnotation.AFTER, methodsAfter);
            }
        }
        return annotationMethodMap;
    }

    private static Integer processBefore(Object object, Map<TestAnnotation, List<Method>> methods) {
        int i = 0;
        for (int j = 0; j < methods.get(TestAnnotation.BEFORE).size(); j++) {
            try {
                methods.get(TestAnnotation.BEFORE).get(j).invoke(object);
                i++;
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    private static Integer processAfter(Object object, Map<TestAnnotation, List<Method>> methods) {
        int i = 0;
        for (int j = 0; j < methods.get(TestAnnotation.AFTER).size(); j++) {
            try {
                methods.get(TestAnnotation.AFTER).get(j).invoke(object);
                i++;
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return i;
    }

    private static Integer processTest(Object object, Map<TestAnnotation, List<Method>> methods) {
        int i = 0;
        for (int j = 0; j < methods.get(TestAnnotation.TEST).size(); j++) {
            try {
                methods.get(TestAnnotation.TEST).get(j).invoke(object);
                i++;
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return i;
    }
}