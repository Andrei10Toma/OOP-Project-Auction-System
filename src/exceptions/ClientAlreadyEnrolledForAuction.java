package exceptions;

/**
 * Exception for the case when the client tries to enter the auction more than once for the same product.
 */
public class ClientAlreadyEnrolledForAuction extends Exception {
    /**
     * Constructor for the exception.
     * @param message message of the exception.
     */
    public ClientAlreadyEnrolledForAuction(String message) {
        super(message);
    }
}
