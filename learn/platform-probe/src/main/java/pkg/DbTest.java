package pkg;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

public class DbTest {
    /**
     * Returns a string describing the outcome of testing the database connection.
     */
    public static String run() {
        String lastStep = "";
        try {
            lastStep = "lookup jdbc/appDb";
            DataSource ds = lookup("jdbc/appDb");
            lastStep = "establish connection";
            try (Connection conn = ds.getConnection()) {
                lastStep = "read meta-data";
                DatabaseMetaData meta = conn.getMetaData();
                return "Connected to " + meta.getDatabaseProductName() + " " + meta.getDatabaseProductVersion();
            }
        } catch (Throwable e) {
            return processThrowable(e, lastStep);
        }
    }

    @SuppressWarnings("unchecked")
    static <T> T lookup(String envName) throws NamingException {
        InitialContext ctx = new InitialContext();
        return (T) ctx.lookup("java:comp/env/" + envName);
    }

    static String processThrowable(Throwable e, String lastStep) {
        String msg = "Failed to " + lastStep + ": " + e;
        Throwable cause = e.getCause();

        while (cause != null) {
            msg += "\n" + cause;
            cause = cause.getCause();
        }
        e.printStackTrace();
        return msg;
    }

}
