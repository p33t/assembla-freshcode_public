package pkg;

import org.apache.catalina.realm.SaltedDataSourceRealm;

import javax.xml.bind.DatatypeConverter;
import java.util.Arrays;

@SuppressWarnings({"deprecation", "UnusedDeclaration"})
@Deprecated
public class SaltingRealm extends SaltedDataSourceRealm {
    protected static final String name = "TestRealm";

    /**
     * The name in JNDI under which to find password digest service.
     */
    protected String digestServiceName;

    /**
     * Indicates where to resolve the password digest service.
     *
     * @see #localDataSource
     */
    protected boolean localDigestService;

    @Override
    protected boolean compareCredentials(String userPassword, String salt, String serverHash) {
        if (userPassword == null || salt == null || serverHash == null) return false;
        byte[] saltArr = DatatypeConverter.parseBase64Binary(salt);
        SaltingService api = lookupService();
        byte[] mutated = api.digestPassword(userPassword, saltArr);
        byte[] serverHashArr = DatatypeConverter.parseBase64Binary(serverHash);
        return Arrays.equals(mutated, serverHashArr);
    }

    /**
     * Retrieve the SaltingService from JNDI.
     */
    protected SaltingService lookupService() {
//        try {
//            Context context;
//            if (localSaltingService) {
//                context = ContextBindings.getClassLoader();
//                context = (Context) context.lookup("comp/env");
//            } else {
//                context = getServer().getGlobalNamingContext();
//            }
//            return (SaltingService) context.lookup(saltingServiceName);
//        } catch (NamingException e) {
//            throw new RuntimeException(e);
//        }
        // Pretend I got this from JNDI
        return new SaltingServiceImpl();
    }

    public boolean getLocalDigestService() {
        return localDigestService;
    }

    public void setLocalDigestService(boolean localDigestService) {
        this.localDigestService = localDigestService;
    }

    @Override
    protected String getName() {
        return name;
    }

    public String getDigestServiceName() {
        return digestServiceName;
    }

    public void setDigestServiceName(String digestServiceName) {
        this.digestServiceName = digestServiceName;
    }
}
