package biz.freshcode.learn.gwtp.client.util;

import com.google.gwt.core.client.GWT;

import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class AppClientUtil {
    public static final Logger LOG = new Log();

    /**
     * A logger that logs to both dev console and javascript console.
     */
    private static class Log extends java.util.logging.Logger {
        private final Logger delegate = Logger.getLogger("App");

        public Log() {
            super("App", null);
        }

        @Override
        public void log(LogRecord record) {
            delegate.log(record);
            GWT.log(record.getMessage(), record.getThrown());
        }
    }
}
