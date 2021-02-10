package employee;

import product.Product;
import product.ProductType;

import java.util.Map;

/**
 * the actions that can be performed by an employee.
 */
public interface Employee {
    /**
     * adds the product to the auction house.
     * @param productMap map of the products
     * @param type type of the product.
     * @param name name of the product.
     * @param minPrice minimum price of the product.
     * @param year fabrication year of the product.
     * @param elem1 first characteristic element.
     * @param elem2 second characteristic element.
     */
    void addProduct(Map<Integer, Product> productMap, ProductType type, String name, double minPrice, int year, String elem1,
                    String elem2);

    /**
     * deletes the product from the auction house.
     * @param productId the product that will be deleted.
     * @param productMap map of the products from the auction house.
     * @param winnerBid the winner bid for the product (sell price of the product).
     */
    void deleteProduct(int productId, Map<Integer, Product> productMap, double winnerBid);
}
