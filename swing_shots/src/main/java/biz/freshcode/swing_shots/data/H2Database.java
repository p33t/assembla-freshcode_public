package biz.freshcode.swing_shots.data;

import org.h2.jdbcx.JdbcConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.List;

import static biz.freshcode.swing_shots.config.Bootstrap.APP_HOME;
import static java.io.File.separator;

/**
 * A utility class to police all interactions with JdbcTemplate and the database.
 * Handy logging of SQL can happen here too.
 */
@Component
@Scope("prototype")
public class H2Database implements InitializingBean {
    public static final String DB_PATH = APP_HOME.get() + separator + "data";
    private final Logger log;
    private JdbcTemplate jt = null;
    private final String name;
    @Inject private Runtime runtime;

    public H2Database(String name) {
        this.name = name;
        // Specialized logger to distinguish messages for different db's
        log = LoggerFactory.getLogger(getClass().getName() + "[" + name + "]");
    }

    public void open() {
        log.info("Opening database...\n   NOTE: If this doesn't work remember the stack traces are saved in " + DB_PATH);
        String s = "jdbc:h2:" + DB_PATH + separator + name;
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

    public void closeIfNecessary() {
        if (isOpen()) close();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // prototype beans cannot use DisposableBean by default.
        runtime.addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                closeIfNecessary();
            }
        }));
    }

    private JdbcConnectionPool pool() {
        return (JdbcConnectionPool) jt.getDataSource();
    }
}
