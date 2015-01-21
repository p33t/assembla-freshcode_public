package biz.freshcode.learn.gwt2.common.client.util;

import java.util.logging.Logger;

public class ClientUtil {
    public static final Logger LOG = Logger.getLogger("App");

    public static void log(String msg) {
        LOG.info(msg);
    }
}
