package pkg.server.boot;

import org.h2.jdbcx.JdbcDataSource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import static pkg.server.util.AppServerUtil.LOG;

@WebListener()
public class AppListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOG.info("Context intialised: " + servletContextEvent.getServletContext().getServerInfo());

        JdbcDataSource ds;
        ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:mem:appDb");

//        try {
//            ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/appDb");
//        } catch (NamingException e) {
//            throw new RuntimeException(e);
//        }

        describe(ds);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOG.info("Context destroyed: " + servletContextEvent.getServletContext().getServerInfo());
    }

    private void describe(DataSource ds) {
        try (Connection conn = ds.getConnection()) {
            DatabaseMetaData meta = conn.getMetaData();
            LOG.info("Connected to " + meta.getDatabaseProductName() + " " + meta.getDatabaseProductVersion());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
