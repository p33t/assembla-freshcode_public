package pkg;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

@SuppressWarnings("deprecation")
@Deprecated
public class SaltingServiceImpl implements SaltingService {
    // Using recommendations from:
    // http://www.javacodegeeks.com/2012/05/secure-password-storage-donts-dos-and.html
    public static final int SEED_SIZE = 8;
    public static final int KEY_LEN = 160; // bits
    public static final int ITERATIONS = 20000;

    @Override
    public byte[] generateSalt() {
        try {
            return SecureRandom.getInstance("SHA1PRNG").generateSeed(SEED_SIZE);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public byte[] digestPassword(String password, byte[] salt) {
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
}
