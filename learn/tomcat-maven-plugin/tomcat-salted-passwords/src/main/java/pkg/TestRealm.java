package pkg;

import org.apache.catalina.realm.SaltedDataSourceRealm;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class TestRealm extends SaltedDataSourceRealm {
    protected static final String name = "TestRealm";

    // Using recommendations from:
    // http://www.javacodegeeks.com/2012/05/secure-password-storage-donts-dos-and.html
    public static final int SEED_SIZE = 8;
    public static final int KEY_LEN = 160; // bits
    public static final int ITERATIONS = 20000;

    public static byte[] generateSalt() {
        try {
            return SecureRandom.getInstance("SHA1PRNG").generateSeed(SEED_SIZE);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

    public static byte[] mutatePassword(String password, byte[] salt) {
        SecretKeyFactory skf;
        try {
            skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, ITERATIONS, KEY_LEN);
        try {
            return skf.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected boolean compareCredentials(String userPassword, String salt, String serverHash) {
        if (userPassword == null || salt == null || serverHash == null) return false;
        byte[] saltArr = DatatypeConverter.parseBase64Binary(salt);
        byte[] mutated = mutatePassword(userPassword, saltArr);
        byte[] serverHashArr = DatatypeConverter.parseBase64Binary(serverHash);
        return Arrays.equals(mutated, serverHashArr);
    }

    @Override
    protected String getName() {
        return name;
    }
}
