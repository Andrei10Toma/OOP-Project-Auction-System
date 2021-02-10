package exceptions;

/**
 * Exception for the case when 2 products with the same id are introduced in the auction house.
 */
public class DuplicateProductException extends Exception {
    /**
     * Constructor for the exception.
     * @param message message of the exception.
     */
    public DuplicateProductException(String message) {
        super(message);
    }
}
