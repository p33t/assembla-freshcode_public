package pkg;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
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
        PasswordMutationService pms;
        try {
            InitialContext ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/appDb");
            pms =  (PasswordMutationService) ctx.lookup("java:comp/env/bean/appPasswordMutation");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }

        primeDb(ds, pms);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOG.info("Context destroyed: " + servletContextEvent.getServletContext().getServerInfo());
    }

    private void primeDb(DataSource ds, PasswordMutationService pms) {
        try (Connection conn = ds.getConnection()) {
            DatabaseMetaData meta = conn.getMetaData();
            LOG.info("Connected to " + meta.getDatabaseProductName() + " " + meta.getDatabaseProductVersion());

//            byte[] saltArr = salter.generateSalt();
//            String theSalt = DatatypeConverter.printBase64Binary(saltArr);
//            byte[] passwordArr = salter.digestPassword("bruce", saltArr);
//            String theCred = DatatypeConverter.printBase64Binary(passwordArr);
            String theCred = pms.mutatePassword("bruce");

            PreparedStatement primeUserCred = conn.prepareStatement(
                    "CREATE TABLE usercred (username VARCHAR(32), " +
                            "userpassword VARCHAR(32)," +
//                            "usersalt VARCHAR(64)," +
                            "usercred VARCHAR(64)" +
                            ");" +
//                            "INSERT INTO usercred VALUES ('bruce', 'bruce', '" + theSalt + "', '" + theCred + "');"
                            "INSERT INTO usercred VALUES ('bruce', 'bruce', '" + theCred + "');"
            );
            primeUserCred.execute();
            LOG.info("usercred table primed with 'bruce' / 'bruce'");


            PreparedStatement primeUserRole = conn.prepareStatement(
                    "CREATE TABLE userrole (username VARCHAR(32), userrole VARCHAR(32));" +
                            "INSERT INTO userrole VALUES ('bruce', 'authentic');" +
                            "INSERT INTO userrole VALUES ('bruce', 'privilege');"
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
