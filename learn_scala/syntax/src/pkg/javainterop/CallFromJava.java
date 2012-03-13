package pkg.javainterop;

public class CallFromJava {
    public static void main(String[] args) throws ClassNotFoundException {
        Simple s = new Simple();
        System.out.println(s.hello("Bruce"));

//Doesn't seem to be necessary...        Class.forName("pkg.javainterop.AnObject$");
        int hundy = pkg.javainterop.AnObject$.MODULE$.plus1(99);
        System.out.println("Want 100: " + hundy);
    }
}
