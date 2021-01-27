package exceptions;

public class AdministratorCantDeleteProducts extends Exception {
    public AdministratorCantDeleteProducts(String message) {
        super(message);
    }
}
