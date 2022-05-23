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
        Map<TestAnnotation, List<Method>> annotationMethodMap = getTestAnnotations(testMethods);
        int [] count = processTest(clazz, annotationMethodMap);
        int allA = annotationMethodMap.get(TestAnnotation.BEFORE).size();
        int allB = annotationMethodMap.get(TestAnnotation.TEST).size();
        int allC = annotationMethodMap.get(TestAnnotation.AFTER).size();
        System.out.println("\nAll Before = " + allA);
        System.out.println("Success Before = " + count[0]);
        System.out.println("Failed Before = " + (allA - count[0]));
        System.out.println("========================================");
        System.out.println("All Test = " + allB);
        System.out.println("Success Test = " + count[1]);
        System.out.println("Failed Test = " + (allB - count[1]));
        System.out.println("========================================");
        System.out.println("All After = " + allC);
        System.out.println("Success After = " + count[2]);
        System.out.println("Failed After = " + (allC - count[2]));

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

    private static int[] processTest(Class clazz, Map<TestAnnotation, List<Method>> methods) {
        int[] count = new int[3];
        for (int j = 0; j < methods.get(TestAnnotation.TEST).size(); j++) {
            try {
                Object object = ReflectionHelper.instantiate(clazz);
                count[0] = processBefore(object, methods);
                methods.get(TestAnnotation.TEST).get(j).invoke(object);
                count[2] = processAfter(object, methods);
                count[1]++;
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return count;
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


}