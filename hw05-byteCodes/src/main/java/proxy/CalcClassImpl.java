package proxy;


import annotation.Log;

public class CalcClassImpl implements CalcClass {

    @Log
    public void calculation(int param1) {
        System.out.println("Param1 = " + param1);
    }

    @Log
    public void calculation(int param1, int param2) {
        System.out.println("Param1 = " + param1 + ", param2 = " + param2);
    }

    @Log
    public void calculation(int param1, int param2, String param3) {
        System.out.println("Param1 = " + param1 + ", param2 = " + param2 + ", param3 = " + param3);
    }
}
