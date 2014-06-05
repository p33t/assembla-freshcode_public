package pkg;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;
import javax.xml.ws.Provider;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

/**
 * Implements configurable password mutation and verification.
 * It implements {@link javax.xml.ws.Provider} so that applications can be decoupled.
 */
@SuppressWarnings("UnusedDeclaration")
public class PasswordMutator implements Provider<String> {
    // These will ultimately be configurable.
    // Default to recommendations from:
    // http://www.javacodegeeks.com/2012/05/secure-password-storage-donts-dos-and.html
    private int seedNumBytes = 8;
    private int keyNumBits = 160; // bits
    private int digestIterationCount = 20000;
    private String secureRandomAlgorithm = "SHA1PRNG";
    private String pbeKeyAlgorithm = "PBKDF2WithHmacSHA1";

    /**
     * Mutates the password for storage and later verification against supplied login credentials.
     * This method will return different values when called with the same password.
     * Note that the 'salt' is also stored in the resulting string.
     *
     * @param password The password to be mutated.
     * @return A string that can be used to verify login credentials.
     * @see #verifyMutatedPassword(String, String)
     */
    public String mutatePassword(String password) {
        byte[] salt = generateSalt();
        byte[] digest = digestPassword(password, salt);
        byte[] result = new byte[salt.length + digest.length];
        System.arraycopy(salt, 0, result, 0, salt.length);
        System.arraycopy(digest, 0, result, salt.length, digest.length);
        return DatatypeConverter.printBase64Binary(result);
    }

    /**
     * Confirm the given password was used to create the given stored mutation.
     *
     * @param candidatePassword     The password supplied by a user that wants to be authenticated.
     * @param storedMutatedPassword A mutation of the users password retrieved from storage.
     */
    public boolean verifyMutatedPassword(String candidatePassword, String storedMutatedPassword) {
        byte[] mute = DatatypeConverter.parseBase64Binary(storedMutatedPassword);
        byte[] salt = new byte[seedNumBytes];
        System.arraycopy(mute, 0, salt, 0, salt.length);
        byte[] digest = new byte[mute.length - salt.length];
        System.arraycopy(mute, salt.length, digest, 0, digest.length);
        byte[] candidate = digestPassword(candidatePassword, salt);
        return Arrays.equals(digest, candidate);
    }

    /**
     * Adapts to common interface to prevent container coupling.
     *
     * @see #mutatePassword(String)
     */
    @Override
    public String invoke(String request) {
        return mutatePassword(request);
    }

    private byte[] generateSalt() {
        try {
            return SecureRandom.getInstance(secureRandomAlgorithm).generateSeed(seedNumBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private byte[] digestPassword(String password, byte[] salt) {
        SecretKeyFactory skf;
        try {
            skf = SecretKeyFactory.getInstance(pbeKeyAlgorithm);
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

    public String getPbeKeyAlgorithm() {
        return pbeKeyAlgorithm;
    }

    public void setPbeKeyAlgorithm(String pbeKeyAlgorithm) {
        this.pbeKeyAlgorithm = pbeKeyAlgorithm;
    }

    public String getSecureRandomAlgorithm() {
        return secureRandomAlgorithm;
    }

    public void setSecureRandomAlgorithm(String secureRandomAlgorithm) {
        this.secureRandomAlgorithm = secureRandomAlgorithm;
    }

    public int getSeedNumBytes() {
        return seedNumBytes;
    }

    public void setSeedNumBytes(int seedNumBytes) {
        this.seedNumBytes = seedNumBytes;
    }
}
