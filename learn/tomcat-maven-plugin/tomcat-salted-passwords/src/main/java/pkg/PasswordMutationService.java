package pkg;

/**
 * A service for mutating passwords with salt.
 * Note that the same password should yield different mutations every time.
 * <br/>
 * Deprecated in favour of a common interface.
 * @see javax.xml.ws.Provider
 * @see pkg.PasswordMutator
 */
@SuppressWarnings("UnusedDeclaration")
@Deprecated
public interface PasswordMutationService {
    /**
     * Mutates the given password for storage purposes.
     * The 'salt' must be coded into the result so that it can be extracted later.
     */
    String mutatePassword(String password);

    /**
     * Confirm the given password was used to create the given stored mutation.
     *
     * @param candidatePassword     The password supplied by a user that wants to be authenticated.
     * @param storedMutatedPassword A mutation of the users password retrieved from storage.
     */
    boolean verifyMutatedPassword(String candidatePassword, String storedMutatedPassword);
}
