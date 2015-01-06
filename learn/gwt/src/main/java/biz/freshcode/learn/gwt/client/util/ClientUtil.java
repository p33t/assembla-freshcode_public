package biz.freshcode.learn.gwt.client.util;

import java.util.logging.Logger;

public class ClientUtil {
    public static final Logger LOG = Logger.getLogger("FileReadButton");

    public static void log(String msg) {
        // NOTE: Only sever works by default
        // https://groups.google.com/forum/#!topic/google-web-toolkit-contributors/Tqu5Zw5H-II
        LOG.severe(msg);
    }
}
