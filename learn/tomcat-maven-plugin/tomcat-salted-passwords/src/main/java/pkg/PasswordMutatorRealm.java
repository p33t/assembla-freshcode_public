/*
This file downloaded from Tomcat suggestion:
http://tomcat.10.x6.nabble.com/support-for-salted-passwords-td5011904.html
 */
package pkg;

import org.apache.catalina.realm.DataSourceRealm;
import org.apache.catalina.realm.GenericPrincipal;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.naming.ContextBindings;

import javax.naming.Context;
import javax.naming.NamingException;
import java.security.Principal;
import java.sql.Connection;
import java.util.ArrayList;

/**
 * A customised DataSourceRealm that delegates password verification to a {@link PasswordMutator} instance,
 * which is obtained from JNDI.
 *
 * @see pkg.PasswordMutator
 */
@SuppressWarnings("UnusedDeclaration")
public class PasswordMutatorRealm extends DataSourceRealm {
    protected static final String name = PasswordMutatorRealm.class.getSimpleName();
    private static final Log log = LogFactory.getLog(PasswordMutatorRealm.class);

    private String passwordMutatorName;
    private boolean localPasswordMutator;

    @Override
    protected Principal authenticate(Connection dbConnection, String username, String credentials) {
        String stored = getPassword(dbConnection, username);
        PasswordMutator service = lookupService();
        boolean validated = service.verifyMutatedPassword(credentials, stored);

        if (containerLog.isTraceEnabled()) {
            String msgKey = validated ? "dataSourceRealm.authenticateSuccess" : "dataSourceRealm.authenticateFailure";
            containerLog.trace(sm.getString(msgKey, username));
        }

        if (!validated) return (null);

        ArrayList<String> list = getRoles(dbConnection, username);
        return new GenericPrincipal(username, credentials, list);
    }

    /* NOTE: Use this method instead of 'authenticate()' if targeting tomcat 7.0.50 or later (?)
    @Override
    protected boolean compareCredentials(String userCredentials, String serverCredentials) {
        PasswordMutator service = lookupService();
        return service.verifyMutatedPassword(userCredentials, serverCredentials);
    }
    */

    /**
     * Retrieve the SaltingService from JNDI.
     */
    protected PasswordMutator lookupService() {
        try {
            Context context;
            if (localPasswordMutator) {
                context = ContextBindings.getClassLoader();
                context = (Context) context.lookup("comp/env");
            } else {
                context = getServer().getGlobalNamingContext();
            }
            return (PasswordMutator) context.lookup(passwordMutatorName);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPasswordMutatorName() {
        return passwordMutatorName;
    }

    public void setPasswordMutatorName(String passwordMutatorName) {
        this.passwordMutatorName = passwordMutatorName;
    }

    @Override
    protected String getName() {
        return name;
    }

    public boolean isLocalPasswordMutator() {
        return localPasswordMutator;
    }

    public void setLocalPasswordMutator(boolean localPasswordMutator) {
        this.localPasswordMutator = localPasswordMutator;
    }
}

