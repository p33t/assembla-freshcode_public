package pkg;

public class DoubleVsFloat {
    public static void main(String[] args) {
        float f = 1.00000001f; // ~9 digits before needing double accuracy.
        String same = (1.0f == f) ? "same" : "different";
        System.out.println("Same?:" + same);
    }
}
