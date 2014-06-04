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
 * A customised DataSourceRealm that delegates password verification to a PasswordMutationService,
 * which is obtained from JNDI.
 *
 * @see pkg.PasswordMutationService
 */
@SuppressWarnings("UnusedDeclaration")
public class PasswordMutationRealm extends DataSourceRealm {
    protected static final String name = PasswordMutationRealm.class.getSimpleName();
    private static final Log log = LogFactory.getLog(PasswordMutationRealm.class);

    private String digestServiceName;
    private boolean localDigestService;

    @Override
    protected Principal authenticate(Connection dbConnection, String username, String credentials) {
        String stored = getPassword(dbConnection, username);
        PasswordMutationService service = lookupService();
        boolean validated = service.verifyMutatedPassword(credentials, stored);

        if (containerLog.isTraceEnabled()) {
            String msgKey = validated ? "dataSourceRealm.authenticateSuccess" : "dataSourceRealm.authenticateFailure";
            containerLog.trace(sm.getString(msgKey, username));
        }

        if (!validated) return (null);

        ArrayList<String> list = getRoles(dbConnection, username);
        return new GenericPrincipal(username, credentials, list);
    }

    /**
     * Retrieve the SaltingService from JNDI.
     */
    protected PasswordMutationService lookupService() {
        try {
            Context context;
            if (localDigestService) {
                context = ContextBindings.getClassLoader();
                context = (Context) context.lookup("comp/env");
            } else {
                context = getServer().getGlobalNamingContext();
            }
            return (PasswordMutationService) context.lookup(digestServiceName);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDigestServiceName() {
        return digestServiceName;
    }

    public void setDigestServiceName(String digestServiceName) {
        this.digestServiceName = digestServiceName;
    }

    @Override
    protected String getName() {
        return name;
    }

    public boolean isLocalDigestService() {
        return localDigestService;
    }

    public void setLocalDigestService(boolean localDigestService) {
        this.localDigestService = localDigestService;
    }
}

