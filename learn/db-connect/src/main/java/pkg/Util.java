package pkg;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Various utility methods.
 */
public class Util {
    public static void withConnection(ConnectionOp op) {
        DataSource ds = getDs();

        if (ds == null) {
            throw new RuntimeException("No datasource found.");
        }

        Connection conn = null;
        try {
            conn = ds.getConnection();
            op.run(conn);

        } catch (RuntimeException re) {
            throw re;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeConn(conn);
        }

    }

    private static void closeConn(Connection conn) {
        if (conn == null) return;
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static DataSource getDs() {
        InitialContext cxt = getIc();

        DataSource ds = null;
        try {
            ds = (DataSource) cxt.lookup("java:/comp/env/jdbc/appDb");
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return ds;
    }

    private static InitialContext getIc() {
        InitialContext cxt = null;
        try {
            cxt = new InitialContext();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        return cxt;
    }
}
