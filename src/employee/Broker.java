package employee;

import client.Client;
import exceptions.ProductNotFound;
import product.Product;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.lang3.tuple.Pair;

public class Broker implements Employee {
    // a map with keys: auctionId and values: the clients that are assigned to this broker that will attend the auction
    private Map<Integer, List<Pair<Client, Double>>> clients = new TreeMap<>();
    private int accumulatedSum;
    private final int id;
    private static int counterBroker = 1;

    public Broker() {
        this.id = counterBroker;
        counterBroker++;
    }

    public Map<Integer, List<Pair<Client, Double>>> getClients() {
        return clients;
    }

    public int getId() {
        return id;
    }

    public void setClients(Map<Integer, List<Pair<Client, Double>>> clients) {
        this.clients = clients;
    }

    public int getAccumulatedSum() {
        return accumulatedSum;
    }

    public void setAccumulatedSum(int accumulatedSum) {
        this.accumulatedSum = accumulatedSum;
    }

    @Override
    public void addProduct(Product product, Map<Integer, Product> productMap) {
        System.out.println("Brokers can't add products to the auction house");
    }

    @Override
    public void deleteProduct(int productId, Map<Integer, Product> productMap) {
        try {
            if (productMap.containsKey(productId)) {
                productMap.remove(productId);
            } else {
                throw new ProductNotFound("Product with " + productId + " was not found.");
            }
        } catch (ProductNotFound e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Broker_" + id + "{" +
                "clientsMap=" + clients +
                ", accumulatedSum=" + accumulatedSum +
                "}\n";
    }
}
