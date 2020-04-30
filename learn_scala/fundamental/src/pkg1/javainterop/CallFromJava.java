package pkg1.javainterop;

public class CallFromJava {
    public static void main(String[] args) throws ClassNotFoundException {
        Simple s = new Simple();
        System.out.println(s.hello("Bruce"));

//Not necessary...        Class.forName("pkg1.javainterop.AnObject$");
        int hundy = AnObject$.MODULE$.plus1(99);
        System.out.println("Want 100: " + hundy);
    }
}
