package auction_house;

import client.Client;
import product.Product;

import java.util.Map;

public interface IAdapter {
    void readClient(Map<Integer, Client> clientMap);
    void readProduct(Map<Integer, Product> productMap);
}
