package exceptions;

public class NotEnoughBrokers extends Exception {
    public NotEnoughBrokers(String message) {
        super(message);
    }
}
