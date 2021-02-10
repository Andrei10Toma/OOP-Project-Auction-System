package employee;

import auction_house.IAdapter;
import product.Product;
import product.ProductFactory;
import product.ProductType;

import java.util.Map;

/**
 * representation of an administrator object.
 */
public class Administrator implements Employee {
    private static Administrator instance;

    /**
     * get the unique instance of the administrator object.
     * @return the instance of the administrator.
     */
    public static Administrator getInstance() {
        if (instance == null) {
            instance = new Administrator();
        }
        return instance;
    }

    /**
     * read products from the JSON file.
     * @param adapter the adapter that will read from JSON file.
     * @param productMap the product map from the auction house.
     */
    public void readProducts(IAdapter adapter, Map<Integer, Product> productMap) {
        adapter.readProduct(productMap);
    }

    /**
     * adds the product from the auction house.
     * @param productMap map of the products
     * @param type type of the product.
     * @param name name of the product.
     * @param minPrice minimum price of the product.
     * @param year fabrication year of the product.
     * @param elem1 first characteristic element.
     * @param elem2 second characteristic element.
     */
    @Override
    public void addProduct(Map<Integer, Product> productMap, ProductType type, String name, double minPrice,
                           int year, String elem1, String elem2) {
        ProductFactory productFactory = new ProductFactory();
        Product productAdd = productFactory.getProduct(type, name, minPrice, year, elem1, elem2);
        productMap.put(productAdd.getId(), productAdd);
    }

    /**
     * deletes the product from the auction house at the end of the auction.
     * @param productId the product that will be deleted.
     * @param productMap map of the products from the auction house.
     * @param winnerBid the winner bid for the product (sell price of the product).
     */
    @Override
    public void deleteProduct(int productId, Map<Integer, Product> productMap, double winnerBid) {
        System.out.println("Administrator can't delete products.");
    }
}
