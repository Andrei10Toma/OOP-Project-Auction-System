package employee;

import exceptions.AdministratorCantDeleteProducts;
import exceptions.DuplicateProductException;
import product.Product;

import java.util.Map;

public class Administrator implements Employee {
    private static Administrator instance;

    public static Administrator getInstance() {
        if (instance == null) {
            instance = new Administrator();
        }
        return instance;
    }

    @Override
    public void addProduct(Product productAdd, Map<Integer, Product> productMap) {
        if (productMap.containsKey(productAdd.getId())) {
            try {
                throw new DuplicateProductException("Product with " + productAdd.getId() + " already exists.");
            } catch (DuplicateProductException e) {
                e.printStackTrace();
            }
        }
        else {
            productMap.put(productAdd.getId(), productAdd);
        }
    }

    @Override
    public void deleteProduct(int productId, Map<Integer, Product> productMap) {
        try {
            throw new AdministratorCantDeleteProducts("Administrators can't delete products.");
        }
        catch (AdministratorCantDeleteProducts e) {
            e.printStackTrace();
        }
    }
}
