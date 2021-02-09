package auction_house;

import employee.Employee;
import product.Product;

import java.util.Map;

public class DeleteProduct implements Runnable {
    private final Employee broker;
    private final Map<Integer, Product> productMap;
    private final Map<Integer, Product> soldProducts;
    private final int id;
    private final double winnerBid;

    public DeleteProduct(Employee broker, Map<Integer, Product> productMap, Map<Integer, Product> soldProducts, int id, double winnerBid) {
        this.productMap = productMap;
        this.soldProducts = soldProducts;
        this.id = id;
        this.broker = broker;
        this.winnerBid = winnerBid;
    }

    @Override
    public void run() {
        synchronized (productMap) {
            broker.deleteProduct(id, productMap, soldProducts, winnerBid);
        }
    }
}
