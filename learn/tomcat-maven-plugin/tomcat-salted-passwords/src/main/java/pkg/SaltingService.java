package pkg;

/**
 * A salt generation and password digest API which can be placed in JNDI for webapps to use.
 * Particulars will be provided directly to the service provider in container config.
 */
public interface SaltingService {
    /**
     * Generate a salt to be stored along side the password.
     */
    byte[] generateSalt();

    /**
     * Digest the password with the given salt either for storage or for comparison.
     */
    byte[] digestPassword(String password, byte[] salt);
}
