package exceptions;

public class CommandNotFound extends Exception {
    public CommandNotFound(String message) {
        super(message);
    }
}
