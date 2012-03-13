package pkg.javainterop;

public class CallFromJava {
    public static void main(String[] args) {
        Simple s = new Simple();
        System.out.println(s.hello("Bruce"));
    }
}
