package pkg;

import java.sql.Connection;

/**
 * Perform an operation with a connection.
 */
public interface ConnectionOp {
    void run(Connection conn) throws Exception;
}
