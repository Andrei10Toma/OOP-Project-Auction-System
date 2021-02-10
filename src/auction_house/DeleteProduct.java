package auction_house;

import employee.Employee;
import product.Product;

import java.util.Map;

/**
 * class to delete the product from the product map when the auction finishes.
 */
public class DeleteProduct implements Runnable {
    private final Employee broker;
    private final Map<Integer, Product> productMap;
    private final int id;
    private final double winnerBid;

    /**
     * constructor for DeleteProduct object
     * @param broker broker that will delete the product from the product map.
     * @param productMap the product map.
     * @param id the id of the product that will be deleted.
     * @param winnerBid the winner bid.
     */
    public DeleteProduct(Employee broker, Map<Integer, Product> productMap, int id, double winnerBid) {
        this.productMap = productMap;
        this.id = id;
        this.broker = broker;
        this.winnerBid = winnerBid;
    }

    /**
     * run method fromm the implementation of the Runnable.
     */
    @Override
    public void run() {
        synchronized (productMap) {
            broker.deleteProduct(id, productMap, winnerBid);
        }
    }
}
