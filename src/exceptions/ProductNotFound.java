package exceptions;

/**
 * Exception for the case when the product is not found in the auction house.
 */
public class ProductNotFound extends Exception {
    /**
     * Constructor for the exception.
     * @param message message of the exception.
     */
    public ProductNotFound(String message) {
        super(message);
    }
}
