package pkg;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import javax.xml.bind.DatatypeConverter;
import java.sql.*;

import static pkg.AppServerUtil.LOG;

@WebListener()
public class AppListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOG.info("Context intialised: " + servletContextEvent.getServletContext().getServerInfo());
        // Stepping stone on way to JNDI data source
//        JdbcDataSource ds;
//        ds = new JdbcDataSource();
//        ds.setURL("jdbc:h2:mem:appDb");

        DataSource ds;
        try {
            ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/appDb");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

        primeDb(ds);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOG.info("Context destroyed: " + servletContextEvent.getServletContext().getServerInfo());
    }

    private void primeDb(DataSource ds) {
        try (Connection conn = ds.getConnection()) {
            DatabaseMetaData meta = conn.getMetaData();
            LOG.info("Connected to " + meta.getDatabaseProductName() + " " + meta.getDatabaseProductVersion());

            byte[] saltArr = TestRealm.generateSalt();
            String theSalt = DatatypeConverter.printBase64Binary(saltArr);
            byte[] passwordArr = TestRealm.mutatePassword("bruce", saltArr);
            String theCred = DatatypeConverter.printBase64Binary(passwordArr);

            PreparedStatement primeUserCred = conn.prepareStatement(
                    "CREATE TABLE usercred (username VARCHAR(32), " +
                            "userpassword VARCHAR(32)," +
                            "usersalt VARCHAR(64)," +
                            "usercred VARCHAR(64)" +
                            ");" +
                            "INSERT INTO usercred VALUES ('bruce', 'bruce', '" + theSalt + "', '" + theCred + "');"
            );
            primeUserCred.execute();
            LOG.info("usercred table primed with 'bruce' / 'bruce'");


            PreparedStatement primeUserRole = conn.prepareStatement(
                    "CREATE TABLE userrole (username VARCHAR(32), userrole VARCHAR(32));" +
                            "INSERT INTO userrole VALUES ('bruce', 'authentic');"
            );
            primeUserRole.execute();
            LOG.info("userrole table primed with 'bruce' / 'authentic'");


            try (ResultSet rs = conn.prepareStatement("SELECT * FROM usercred").executeQuery()) {
                if (!rs.next()) pop("No results");
                String username = rs.getString(1);
                String userpassword = rs.getString(2);
                if (!"bruce".equals(username)) pop("Bad username: " + username);
                if (!"bruce".equals(userpassword)) pop("Bad userpassword: " + userpassword);
            }
            LOG.info("usercred contents confirmed");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void pop(String msg) {
        throw new IllegalStateException(msg);
    }
}
