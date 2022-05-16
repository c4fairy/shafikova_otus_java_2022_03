package annotations.usage;

import annotations.After;
import annotations.Before;
import annotations.Test;

public class TestAnnotations {
    @Before
    public void methodBefore() {
        System.out.println("This method is Before");
    }

    @Test
    public void methodTest1() {
        System.out.println("This method is Test 1");
    }

    @Test
    public void methodTest2() throws IllegalAccessException {
        System.out.println("This method is Test 2");
        throw new IllegalAccessException();
    }

    @After
    public void methodAfter() {
        System.out.println("This method is After");
        System.out.println("========================================");
    }
}
