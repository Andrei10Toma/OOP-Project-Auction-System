package auction_house;

import client.Client;
import product.Product;

import java.util.List;
import java.util.Map;

public interface IAdapter {
    Map<Integer, Client> readClient();
    Map<Integer, Product> readProduct();
}
