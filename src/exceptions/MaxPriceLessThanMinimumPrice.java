package exceptions;

public class MaxPriceLessThanMinimumPrice extends Exception{
    public MaxPriceLessThanMinimumPrice(String message) {
        super(message);
    }
}
