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
    private int seedNumBytes = 8;
    private int keyNumBits = 160; // bits
    private int digestIterationCount = 20000;

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
        byte[] salt = new byte[seedNumBytes];
        System.arraycopy(mute, 0, salt, 0, salt.length);
        byte[] digest = new byte[mute.length - salt.length];
        System.arraycopy(mute, salt.length, digest, 0, digest.length);
        byte[] candidate = digestPassword(candidatePassword, salt);
        return Arrays.equals(digest, candidate);
    }

    private byte[] generateSalt() {
        try {
            return SecureRandom.getInstance("SHA1PRNG").generateSeed(seedNumBytes);
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

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, digestIterationCount, keyNumBits);
        try {
            return skf.generateSecret(spec).getEncoded();
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public int getDigestIterationCount() {
        return digestIterationCount;
    }

    public void setDigestIterationCount(int digestIterationCount) {
        this.digestIterationCount = digestIterationCount;
    }

    public int getKeyNumBits() {
        return keyNumBits;
    }

    public void setKeyNumBits(int keyNumBits) {
        this.keyNumBits = keyNumBits;
    }

    public int getSeedNumBytes() {
        return seedNumBytes;
    }

    public void setSeedNumBytes(int seedNumBytes) {
        this.seedNumBytes = seedNumBytes;
    }
}
