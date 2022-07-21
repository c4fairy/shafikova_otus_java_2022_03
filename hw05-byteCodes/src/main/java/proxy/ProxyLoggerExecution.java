package proxy;

public class ProxyLoggerExecution {
    public static void main(String[] args) {
        CalcClass myClass = ProxyLogger.createMyClass();
        myClass.calculation(0);
        myClass.calculation(1, 2);
        myClass.calculation(1, 2, "pow");
    }
}



