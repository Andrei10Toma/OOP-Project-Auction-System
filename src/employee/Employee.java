package employee;

import product.Product;

import java.util.Map;

public interface Employee {
    void addProduct(Product product, Map<Integer, Product> productMap);
    void deleteProduct(int productId, Map<Integer, Product> productMap);
}
