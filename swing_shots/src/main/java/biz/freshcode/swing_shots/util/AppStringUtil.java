package biz.freshcode.swing_shots.util;

public class AppStringUtil {
    public static String repeat(String s, int times) {
        StringBuilder sb = new StringBuilder(s.length() * times);
        for (int i = 0; i < times; i ++ ) {
            sb.append(s);
        }
        return sb.toString();
    }
}
