package biz.freshcode.learn.gwt2.mod2.client.util;

import com.google.gwt.user.client.Window;

public class ClientUtil {
    /**
     * Alternative to System.lineSeparator() in the browser.
     */
    public static String clientLineSeparator() {
        String platform = Window.Navigator.getPlatform();
        if (platform.toLowerCase().contains("windows")) return "\r\n";
        return "\n";
    }
}
