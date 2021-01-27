package employee;

import client.Client;
import exceptions.BrokerAddProductException;
import exceptions.ProductNotFound;
import product.Product;

import java.util.Map;
import java.util.TreeMap;

public class Broker implements Employee {
    private Map<Integer, Map<Integer, Client>> clientsMap = new TreeMap<>();
    private int accumulatedSum;
    private final int id;

    public Broker(int id) {
        this.id = id;
    }

    public Map<Integer, Map<Integer, Client>> getClientsMap() {
        return clientsMap;
    }

    public int getId() {
        return id;
    }

    public void setClientsMap(Map<Integer, Map<Integer, Client>> clientsMap) {
        this.clientsMap = clientsMap;
    }

    public int getAccumulatedSum() {
        return accumulatedSum;
    }

    public void setAccumulatedSum(int accumulatedSum) {
        this.accumulatedSum = accumulatedSum;
    }

    @Override
    public void addProduct(Product product, Map<Integer, Product> productMap) {
        try {
            throw new BrokerAddProductException("Brokers can't add products");
        }
        catch (BrokerAddProductException e) {
            e.printStackTrace();
        }
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
}
