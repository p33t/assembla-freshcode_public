package pkg1;

/**
 * Strange behaviour of Double comparator.
 */
public class JavaQuirkNegativeZero {
    @SuppressWarnings("ConstantConditions")
    public static void main(String[] args) {
        // looks like a bug to me
        // long standing... http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=6603276
        System.out.println(0.0 == -0.0); // prints 'true'
        System.out.println(Double.compare(0.0, -0.0)); // prints '1'
    }
}
