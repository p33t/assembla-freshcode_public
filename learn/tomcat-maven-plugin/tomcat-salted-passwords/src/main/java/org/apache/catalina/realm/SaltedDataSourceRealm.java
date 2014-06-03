/*
This file downloaded from Tomcat suggestion:
http://tomcat.10.x6.nabble.com/support-for-salted-passwords-td5011904.html
 */
package org.apache.catalina.realm;

import org.apache.catalina.LifecycleException;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.Principal;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Extension of the DataSourceRealm included in Apache Tomcat to handle salted
 * passwords for increased security.
 *
 * @author gsanmar
 */
@SuppressWarnings({"JavaDoc", "UnusedDeclaration"})
public class SaltedDataSourceRealm extends DataSourceRealm {


    // ----------------------------------------------------- Instance Variables


    // The column in the user table that holds the user's salt.
    protected String userSaltCol = null;

    // Descriptive information about this Realm implementation.
    protected static final String name = "SaltedDataSourceRealm";

    // The generated string for the salts PreparedStatement
    private String preparedSalt = null;

    // Logger
    private static final Log log =
            LogFactory.getLog(SaltedDataSourceRealm.class);


    // ------------------------------------------------------------- Properties


    public String getUserSaltCol() {
        return userSaltCol;
    }

    public void setUserSaltCol(String userSaltCol) {
        this.userSaltCol = userSaltCol;
    }

    @Override
    protected String getName() {
        return name;
    }


    // --------------------------------------------------------- Public Methods


    // -------------------------------------------------------- Package Methods


    // ------------------------------------------------------ Protected Methods


    /**
     * Return the Principal associated with the specified username and
     * credentials, if there is one; otherwise return <code>null</code>.
     * <p/>
     * This method differs from its counterpart in parent class DataSourceRealm
     * in that it gets the salt and passes it as an argument for validation.
     *
     * @param dbConnection
     * @param username
     * @param credentials  Password supplied by user, cleartext.
     * @return Principal associated with the specified username and
     * credentials, if there is one; otherwise <code>null</code>
     */
    @Override
    protected Principal authenticate(Connection dbConnection, String username,
                                     String credentials) {
        String dbCredentials = getPassword(dbConnection, username);
        String dbSalt = getSalt(dbConnection, username);

        // Validate the user's credentials
        boolean validated = compareCredentials(credentials, dbSalt,
                dbCredentials);

        if (validated) {
            if (containerLog.isTraceEnabled())
                containerLog.trace(
                        sm.getString("dataSourceRealm.authenticateSuccess",
                                username));
        } else {
            if (containerLog.isTraceEnabled())
                containerLog.trace(
                        sm.getString("dataSourceRealm.authenticateFailure",
                                username));
            return (null);
        }

        ArrayList<String> list = getRoles(dbConnection, username);

        // Create and return a suitable Principal for this user
        return new GenericPrincipal(username, credentials, list);
    }

    /**
     * Return the salt associated with the given username.
     *
     * @param username
     * @return salt associated with the given username
     */
    protected String getSalt(String username) {
        // Ensure that we have an open database connection
        Connection dbConnection = open();
        if (dbConnection == null) {
            return null;
        }

        try {
            return getSalt(dbConnection, username);
        } finally {
            close(dbConnection);
        }
    }

    /**
     * Return the salt associated with the given username.
     *
     * @param dbConnection
     * @param username
     * @return salt associated with the given username
     */
    protected String getSalt(Connection dbConnection, String username) {

        ResultSet rs = null;
        PreparedStatement stmt = null;
        String dbSalt = null;

        try {
            stmt = salt(dbConnection, username);
            rs = stmt.executeQuery();
            if (rs.next()) {
                dbSalt = rs.getString(1);
            }

            return (dbSalt != null) ? dbSalt.trim() : null;

        } catch (SQLException e) {
            containerLog.error(
                    sm.getString("SaltedDataSourceRealm.getSalt.exception",
                            username), e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException e) {
                containerLog.error(
                        sm.getString("SaltedDataSourceRealm.getSalt.exception",
                                username), e);

            }
        }

        return null;
    }

    /**
     * Parses salt and server digest hex strings into bytes, salts and digests
     * the cleartext user password, and compares the resulting digest with the
     * server digest for equality.  This method is used instead of the one in
     * RealmBase because that one does not salt.
     *
     * @param userPassword Password provided by user, cleartext
     * @param salt         Salt added to password before digesting
     * @param serverHash   Salted and digested password stored on the server
     * @return true if salted and digested passwords match, otherwise false
     */
    protected boolean compareCredentials(String userPassword, String salt,
                                         String serverHash) {
        if (userPassword == null || salt == null || serverHash == null) {
            return false;
        }

        byte[] saltBytes = DatatypeConverter.parseHexBinary(salt);
        byte[] serverDigestBytes = DatatypeConverter.parseHexBinary(serverHash);

        // Digest user password
        byte[] userDigestBytes;
        synchronized (this) {
            md.reset();

            // User password
            md.update(userPassword.getBytes());

            // Add salt
            md.update(saltBytes);

            userDigestBytes = md.digest();
        }

        // TODO Constant time comparison to foil timing attacks.
        return Arrays.equals(userDigestBytes, serverDigestBytes);
    }


    // -------------------------------------------------------- Private Methods


    /**
     * Return a PreparedStatement configured to perform the SELECT required
     * to retrieve salt used before digesting the specified user's password.
     *
     * @param dbConnection the database connection to be used
     * @param username     username for which salt is retrieved
     * @return PreparedStatement configured to perform the SELECT required
     * to retrieve salt used before digesting the specified user's password
     * @throws SQLException
     */
    private PreparedStatement salt(Connection dbConnection, String username)
            throws SQLException {
        PreparedStatement salt = dbConnection.prepareStatement(preparedSalt);
        salt.setString(1, username);
        return salt;
    }


    // --------------------------------------------------------- Static Methods


    /**
     * Generate random salt, use it to salt and digest the user's password,
     * and return an object containing the generated random salt and the
     * digested password.  This method can be used to salt and digest password
     * in a way that can later be used for authentication.
     *
     * @param credentials user's password, cleartext
     * @param algorithm   algorithm used to digest
     * @param encoding    character encoding of the string to digest
     * @return object containing the newly generated random salt and password
     * digest
     */
    public static SaltAndDigest saltAndDigest(String credentials,
                                              String algorithm, String encoding) {

        // Number of bytes used to salt.
        // TODO This should be an argument specifyable in the xml config file.
        final int NBYTES = 64;

        // Generate random salt
        SecureRandom r = new SecureRandom();
        byte[] salt = new byte[NBYTES];
        r.nextBytes(salt);
        String saltString = DatatypeConverter.printHexBinary(salt);

        // Hash
        try {
            // Obtain a new message digest with "digest" encryption
            MessageDigest md =
                    (MessageDigest) MessageDigest.getInstance(algorithm).clone();

            // encode the credentials
            // Should use the digestEncoding, but that's not a static field
            if (encoding == null) {
                md.update(credentials.getBytes());
                md.update(salt);
            } else {
                md.update(credentials.getBytes(encoding));
                md.update(salt);
            }

            String digestString = DatatypeConverter.printHexBinary(md.digest());

            // Digest the credentials and return as hexadecimal
            return new SaltAndDigest(saltString, digestString);

        } catch (Exception ex) {
            log.error(ex);
            return null;
        }
    }

    /**
     * Class containing a salt and digest.
     */
    public static class SaltAndDigest {
        private final String salt;
        private final String digest;

        public SaltAndDigest(String salt, String digest) {
            this.salt = salt;
            this.digest = digest;
        }

        public String getSalt() {
            return salt;
        }

        public String getDigest() {
            return digest;
        }

    }


    // ------------------------------------------------------ Lifecycle Methods


    /**
     * Prepare for the beginning of active use of the public methods of this
     * component and implement the requirements of
     * {@link org.apache.catalina.util.LifecycleBase#startInternal()}.
     *
     * @throws LifecycleException if this component detects a fatal error
     *                            that prevents this component from being used
     */
    @Override
    protected void startInternal() throws LifecycleException {

        // Create the salt PreparedStatement string
        @SuppressWarnings("StringBufferReplaceableByString")
        StringBuilder temp = new StringBuilder("SELECT ");
        temp.append(userSaltCol);
        temp.append(" FROM ");
        temp.append(userTable);
        temp.append(" WHERE ");
        temp.append(userNameCol);
        temp.append(" = ?");
        preparedSalt = temp.toString();

        super.startInternal();
    }

}

