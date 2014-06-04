package pkg;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

public class PasswordMutationServiceImpl implements PasswordMutationService {
    // These will ultimately be configurable.
    // Default to recommendations from:
    // http://www.javacodegeeks.com/2012/05/secure-password-storage-donts-dos-and.html
    public static final int SEED_SIZE = 8;
    public static final int KEY_LEN = 160; // bits
    public static final int ITERATIONS = 20000;

    @Override
    public String mutatePassword(String password) {
        byte[] salt = generateSalt();
        byte[] digest = digestPassword(password, salt);
        byte[] result = new byte[salt.length + digest.length];
        System.arraycopy(salt, 0, result, 0, salt.length);
        System.arraycopy(digest, 0, result, salt.length, digest.length);
        return DatatypeConverter.printBase64Binary(result);
    }

    @Override
    public boolean verifyMutatedPassword(String candidatePassword, String storedMutatedPassword) {
        byte[] mute = DatatypeConverter.parseBase64Binary(storedMutatedPassword);
        byte[] salt = new byte[SEED_SIZE];
        System.arraycopy(mute, 0, salt, 0, salt.length);
        byte[] digest = new byte[mute.length - salt.length];
        System.arraycopy(mute, salt.length, digest, 0, digest.length);
        byte[] candidate = digestPassword(candidatePassword, salt);
        return Arrays.equals(digest, candidate);
    }

    private byte[] generateSalt() {
        try {
            return SecureRandom.getInstance("SHA1PRNG").generateSeed(SEED_SIZE);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] digestPassword(String password, byte[] salt) {
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
