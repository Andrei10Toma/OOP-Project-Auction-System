package exceptions;

/**
 * Exception when the client is not found in the auction house.
 */
public class ClientNotFound extends Exception {
    /**
     * Constructor for the exception.
     * @param message message of the exception.
     */
    public ClientNotFound(String message) {
        super(message);
    }
}
