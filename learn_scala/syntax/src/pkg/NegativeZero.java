package pkg;

public class NegativeZero {
    public static void main(String[] args) {
        // looks like a bug to me
        // long standing... http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6603276
        System.out.println(0.0 == -0.0); // prints 'true'
        System.out.println(new Double(0.0).compareTo(-0.0)); // prints '1'
    }
}
