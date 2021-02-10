package exceptions;

/**
 * Exception for the case when 2 clients with the same id are introduced in the auction house.
 */
public class DuplicateClientException extends Exception {
    /**
     * Constructor for the exception.
     * @param message message of the exception.
     */
    public DuplicateClientException(String message) {
        super(message);
    }
}
