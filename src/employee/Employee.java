package employee;

import product.Product;
import product.ProductType;

import java.util.Map;

public interface Employee {
    void addProduct(Map<Integer, Product> productMap, ProductType type, String name, double minPrice, int year, String elem1,
                    String elem2);
    void deleteProduct(int productId, Map<Integer, Product> productMap, Map<Integer, Product> soldProducts, double winnerBid);
}
