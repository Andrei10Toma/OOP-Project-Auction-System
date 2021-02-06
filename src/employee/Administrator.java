package employee;

import auction_house.IAdapter;
import product.Product;
import product.ProductFactory;
import product.ProductType;

import java.util.Map;

public class Administrator implements Employee {
    private static Administrator instance;

    public static Administrator getInstance() {
        if (instance == null) {
            instance = new Administrator();
        }
        return instance;
    }

    public void readProducts(IAdapter adapter, Map<Integer, Product> productMap) {
        adapter.readProduct(productMap);
    }

    @Override
    public synchronized void addProduct(Map<Integer, Product> productMap, ProductType type, String name, double minPrice,
                                        int year, String elem1, String elem2) {
        ProductFactory productFactory = new ProductFactory();
        Product productAdd = productFactory.getProduct(type, name, minPrice, year, elem1, elem2);
        productMap.put(productAdd.getId(), productAdd);
    }

    @Override
    public void deleteProduct(int productId, Map<Integer, Product> productMap) {
        System.out.println("Administrator can't delete products.");
    }
}
