package pkg;

import javax.servlet.http.HttpServletResponse;

public class ServletUtils {
    public static void noCache(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", -1);
    }
}
