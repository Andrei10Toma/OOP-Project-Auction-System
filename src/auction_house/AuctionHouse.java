package auction_house;

import auction.Auction;
import client.*;
import employee.Broker;
import product.Product;

import java.util.*;

public class AuctionHouse {
    private List<Product> products = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();
    private List<Auction> auctions = new ArrayList<>();
    private List<Broker> brokers = new ArrayList<>();
    private static AuctionHouse instance;
    private IAdapter adapter;

    private AuctionHouse(IAdapter adapter) {
        this.adapter = adapter;
    }

    public static AuctionHouse getInstance(IAdapter adapter) {
        if (instance == null) {
            instance = new AuctionHouse(adapter);
        }
        return instance;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Client> getClients() {
        return clients;
    }

    public List<Auction> getAuctions() {
        return auctions;
    }

    public List<Broker> getBrokers() {
        return brokers;
    }

    public void registerClients() {
        clients = adapter.readClient();
    }

    public void registerProducts() {
        products = adapter.readProduct();
    }

    public void listClients() {
        System.out.println(clients);
    }

    public void listProducts() {
        System.out.println(products);
    }
}
