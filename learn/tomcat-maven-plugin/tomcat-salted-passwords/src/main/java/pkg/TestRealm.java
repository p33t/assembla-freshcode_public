package pkg;

import org.apache.catalina.realm.SaltedDataSourceRealm;

import javax.xml.bind.DatatypeConverter;
import java.util.Arrays;

public class TestRealm extends SaltedDataSourceRealm {
    protected static final String name = "TestRealm";

    /**
     * The name in JNDI under which the PasswordMutationService implementation is stored.
     */
    protected String passwordMutationName;

    /**
     * Indicates where to resolve the saltedPasswordName.
     *
     * @see #localDataSource
     */
    protected boolean localPasswordMutation;

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

    public boolean getLocalPasswordMutation() {
        return localPasswordMutation;
    }

    public void setLocalPasswordMutation(boolean localPasswordMutation) {
        this.localPasswordMutation = localPasswordMutation;
    }

    @Override
    protected String getName() {
        return name;
    }

    public String getPasswordMutationName() {
        return passwordMutationName;
    }

    public void setPasswordMutationName(String passwordMutationName) {
        this.passwordMutationName = passwordMutationName;
    }
}
