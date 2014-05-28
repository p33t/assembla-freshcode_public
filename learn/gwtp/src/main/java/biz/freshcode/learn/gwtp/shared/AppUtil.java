package biz.freshcode.learn.gwtp.shared;

public class AppUtil {
    /**
     * The name of the cookie to put the RPC token in for XSRF protection.
     * NOTE: This could possibly vary by app instance to avoid cookie collision with other apps on same server.
     * The cookie name would need to be set in the SessionInfo.
     *
     * ALSO!!!!!! This doesn't seem to work in Chrome in dev mode with address 127.0.0.1.  The cookie is not stored.
     * ====> Using 'localhost' does work.
     */
    public static final String XSRF_COOKIE = "XSRF-SAFETY";
}
