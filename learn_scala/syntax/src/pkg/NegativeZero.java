package pkg;

public class NegativeZero {
    public static void main(String[] args) {
        // looks like a bug to me
        System.out.println(0.0 == -0.0); // prints 'true'
        System.out.println(new Double(0.0).compareTo(-0.0)); // prints '1'
    }
}
