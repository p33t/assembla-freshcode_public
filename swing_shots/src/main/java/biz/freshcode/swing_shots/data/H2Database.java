package biz.freshcode.swing_shots.data;

import biz.freshcode.swing_shots.logging.Logging;
import org.h2.jdbcx.JdbcConnectionPool;
import org.slf4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.jdbc.core.JdbcOperations;
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
    private JdbcOperations ops = null;

    public void open() {
        log.info("Opening database...\n   NOTE: If this doesn't work remember the stack traces are saved in " + DB_PATH);
        String s = "jdbc:h2:" + DB_PATH + separator + "db";
        JdbcConnectionPool pool = JdbcConnectionPool.create(s, "", "");
        ops = new JdbcTemplate(pool);
        log.info("Database successfully opened.");
    }

    public boolean isOpen() {
        return ops != null;
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
            ops = null;
        }
    }

    public void update(String sql, Object... args) {
        ops.update(sql, args);
    }

    public void execute(String sql) {
        ops.execute(sql);
    }

    public <T> List<T> query(String sql, RowMapper<T> mapper, Object... args) {
        return ops.query(sql, mapper, args);
    }

    @Override
    public void destroy() throws Exception {
        if (isOpen()) close();
    }

    private JdbcTemplate template() {
        return (JdbcTemplate) ops;
    }

    private JdbcConnectionPool pool() {
        return (JdbcConnectionPool) template().getDataSource();
    }
}
