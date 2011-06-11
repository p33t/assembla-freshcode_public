package biz.freshcode.swing_shots.data;

import biz.freshcode.swing_shots.logging.Logging;
import org.h2.jdbcx.JdbcConnectionPool;
import org.slf4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

import static biz.freshcode.swing_shots.config.Bootstrap.APP_HOME;
import static java.io.File.separator;

@Component
public class H2Database implements DisposableBean {
    public static final String DB_PATH = APP_HOME.get() + separator + "data";
    @Logging private Logger log;
    private JdbcTemplate jt = null;

    public void open() {
        log.info("Opening database...\n   NOTE: If this doesn't work remember the stack traces are saved in " + DB_PATH);
        String s = "jdbc:h2:" + DB_PATH + separator + "db";
        JdbcConnectionPool pool = JdbcConnectionPool.create(s, "", "");
        jt = new JdbcTemplate(pool);
        log.info("Database successfully opened.");
    }

    public boolean isOpen() {
        return jt != null;
    }

    public void openAndResetIfNecessary() {
        if (!isOpen()) open();
        reset();
    }

    public void reset() {
        execute("DROP ALL OBJECTS;");
    }

    public void close() {
        log.info("Closing database.");
        try {
            pool().dispose();
        } finally {
            jt = null;
        }
    }

    public void update(String sql, Object... args) {
        jt.update(sql, args);
    }

    public void execute(String sql) {
        jt.execute(sql);
    }

    public <T> List<T> query(String sql, RowMapper<T> mapper, Object... args) {
        return jt.query(sql, mapper, args);
    }

    @Override
    public void destroy() throws Exception {
        if (isOpen()) close();
    }

    private JdbcConnectionPool pool() {
        return (JdbcConnectionPool) jt.getDataSource();
    }
}
