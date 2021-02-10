package exceptions;

/**
 * Broker is not found in the auction house.
 */
public class BrokerNotFound extends Exception {
    /**
     * Constructor for the exception.
     * @param message message of the exception.
     */
    public BrokerNotFound(String message) {
        super(message);
    }
}
