package biz.freshcode.swing_shots.data;

import biz.freshcode.swing_shots.logging.Logging;
import org.h2.jdbcx.JdbcConnectionPool;
import org.slf4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import static biz.freshcode.swing_shots.config.Bootstrap.APP_HOME;
import static java.io.File.separator;

@Component
public class H2Database implements DisposableBean {
    public static final String DB_PATH = APP_HOME.get() + separator + "data";
    private JdbcConnectionPool poolOrNull;
    @Logging private Logger log;

    public void openExisting() {
        log.info("Opening database...\n   NOTE: If this doesn't work remember the stack traces are saved in " + DB_PATH);
        String s = "jdbc:h2:" + DB_PATH + separator + "db";
        poolOrNull = JdbcConnectionPool.create(s, "", "");
        log.info("Database successfully opened.");
    }

    public boolean isOpen() {
        return poolOrNull != null;
    }

    public void close() {
        log.info("Closing database.");
        try {
            poolOrNull.dispose();
        } finally {
            poolOrNull = null;
        }
    }

    @Override
    public void destroy() throws Exception {
        if (isOpen()) close();
    }
}
