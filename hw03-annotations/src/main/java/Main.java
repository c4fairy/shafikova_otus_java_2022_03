import annotations.usage.TestAnnotations;
import annotations.usage.TestRunner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        TestRunner.testRun(TestAnnotations.class.getName());
    }
}
