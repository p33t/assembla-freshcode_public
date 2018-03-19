package codechef.numbgame;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NumbGame {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int gameCount = scan.nextInt();
        for (int i = gameCount; i > 0; i--) {
            int a = scan.nextInt();
            int m = scan.nextInt();
            int result = play(a, m);
            System.out.println(result);
        }
    }

    static int play(int a, int m) {
        List<String> possibles = calcPossibles(a);

        int winCount = 0;
        for (String robotMove : possibles) {
            for (String response : possibles) {
                Integer candidate = Integer.valueOf(robotMove + response);
                if (candidate % m == 0) {
                    winCount++;
                    break; // finished with this robot response
                }
            }
        }

        return winCount;
    }

    private static List<String> calcPossibles(int a) {
        final String strA = Integer.toString(a);
        return IntStream.range(0, strA.length())
                .mapToObj(i -> strA.substring(0, i) + strA.substring(i + 1))
                .collect(Collectors.toList());
    }
}
