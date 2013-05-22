package biz.freshcode.learn.gwtp.shared;

public class AppUtil {
    /**
     * The name of the cookie to put the RPC token in for XSRF protection.
     * NOTE: This could possibly vary by app instance to avoid cookie collision with other apps on same server.
     * The cookie name would need to be set in the SessionInfo.
     * See http://code.google.com/p/gwt-platform/issues/detail?id=429
     */
    public static final String XSRF_COOKIE = "XSRF-SAFETY";
}
