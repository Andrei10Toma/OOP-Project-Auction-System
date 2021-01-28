package auction_house;

import employee.Employee;
import product.Product;

import java.util.Map;

public class DeleteProduct implements Runnable {
    private final Employee broker;
    private final Map<Integer, Product> productMap;
    private final int id;

    public DeleteProduct(Employee broker, Map<Integer, Product> productMap, int id) {
        this.productMap = productMap;
        this.id = id;
        this.broker = broker;
    }

    @Override
    public void run() {
        broker.deleteProduct(id, productMap);
    }
}
