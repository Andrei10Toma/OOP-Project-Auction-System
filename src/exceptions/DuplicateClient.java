package exceptions;

public class DuplicateClient extends Exception {
    public DuplicateClient(String message) {
        super(message);
    }
}
