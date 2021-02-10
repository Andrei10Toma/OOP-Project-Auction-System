package auction_house;

import client.Client;
import product.Product;

import java.util.Map;

/**
 * adapter for the read of the JSON files.
 */
public interface IAdapter {
    /**
     * set the filename of the JSON where the products and clients are stored.
     * @param filename the name of the file.
     */
    void setFilename(String filename);

    /**
     * load the clients from the JSON and store them in the clientMap.
     * @param clientMap map of the clients from the auction house.
     */
    void readClient(Map<Integer, Client> clientMap);

    /**
     * load the products from tbe JSON and store them in the productMap.
     * @param productMap map of the products from the auction house.
     */
    void readProduct(Map<Integer, Product> productMap);
}
