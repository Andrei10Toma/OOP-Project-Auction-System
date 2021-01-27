package auction_house;

import employee.Administrator;
import employee.Employee;
import product.Product;

import java.util.Map;

public class AddProduct implements Runnable {
    private final Map<Integer, Product> productMap;
    private final Product productAdd;
    private final Employee admin = Administrator.getInstance();

    public AddProduct(Map<Integer, Product> productMap, Product productAdd) {
        this.productMap = productMap;
        this.productAdd = productAdd;
    }

    @Override
    public void run() {
        synchronized (productMap) {
            admin.addProduct(productAdd, productMap);
        }
    }
}
