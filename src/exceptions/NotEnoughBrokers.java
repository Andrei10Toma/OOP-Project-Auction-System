package exceptions;

/**
 * Exception when one client wants to bid and brokers are not generated.
 */
public class NotEnoughBrokers extends Exception {
    /**
     * Constructor for the exception.
     * @param message message of the exception.
     */
    public NotEnoughBrokers(String message) {
        super(message);
    }
}
