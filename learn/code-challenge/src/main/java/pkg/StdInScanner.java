package pkg;

import java.util.Scanner;

public class StdInScanner {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNextInt()) {
            int i = scan.nextInt();
            System.out.println("Read int: " + i);
        }

        System.out.println("No more integers");
    }
}
