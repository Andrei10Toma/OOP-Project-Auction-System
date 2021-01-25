package auction_house;

import client.Client;
import product.Product;

import java.util.List;

public interface IAdapter {
    List<Client> readClient();
    List<Product> readProduct();
}
