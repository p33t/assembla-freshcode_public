package biz.freshcode.learn.gwt2.common.client.util;

public class AppStringUtil {

    public static String lines(String... lines) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (String line : lines) {
            if (!first) sb.append("\n");
            sb.append(line);
            first = false;
        }
        return sb.toString();
    }

}
