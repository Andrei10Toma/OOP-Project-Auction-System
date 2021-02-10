package auction_house;

import employee.Administrator;
import employee.Employee;
import product.Product;
import product.ProductType;

import java.util.Map;

/**
 * Class for the product add in the product map.
 */
public class AddProduct implements Runnable {
    private final Map<Integer, Product> productMap;
    private final Employee admin = Administrator.getInstance();
    private final String name;
    private final double minPrice;
    private final ProductType type;
    private final int year;
    private final String characteristicElem1;
    private final String characteristicElem2;

    /**
     * Constructor for the add product class.
     * @param productMap product map from the auction house.
     * @param name name of the product.
     * @param minPrice minimum price of the product.
     * @param type type of the product.
     * @param year fabrication year of the product.
     * @param characteristicElem1 characteristic element of the product.
     * @param characteristicElem2 characteristic element of the product.
     */
    public AddProduct(Map<Integer, Product> productMap, String name, double minPrice, ProductType type, int year, String characteristicElem1, String characteristicElem2) {
        this.productMap = productMap;
        this.name = name;
        this.minPrice = minPrice;
        this.type = type;
        this.year = year;
        this.characteristicElem1 = characteristicElem1;
        this.characteristicElem2 = characteristicElem2;
    }

    /**
     * Run method for the multithreading.
     */
    @Override
    public void run() {
        synchronized (productMap) {
            admin.addProduct(productMap, type, name, minPrice, year, characteristicElem1, characteristicElem2);
        }
    }
}
