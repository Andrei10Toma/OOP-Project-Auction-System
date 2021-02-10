package exceptions;

/**
 * Exception for the case when the max bid of the client is less than the minimum price of the product.
 */
public class MaxPriceLessThanMinimumPrice extends Exception{
    /**
     * Constructor for the exception.
     * @param message message of the exception.
     */
    public MaxPriceLessThanMinimumPrice(String message) {
        super(message);
    }
}
