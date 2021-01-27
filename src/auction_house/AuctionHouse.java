package auction_house;

import auction.Auction;
import client.*;
import employee.Broker;
import product.Product;

import java.util.*;

public class AuctionHouse {
    private Map<Integer, Product> products = new TreeMap<>();
    private Map<Integer, Client> clients = new TreeMap<>();
    private Map<Integer, Auction> auctions = new TreeMap<>();
    private Map<Integer, Broker> brokers = new TreeMap<>();
    private static AuctionHouse instance;
    private final IAdapter adapter;

    private AuctionHouse(IAdapter adapter) {
        this.adapter = adapter;
    }

    public static AuctionHouse getInstance(IAdapter adapter) {
        if (instance == null) {
            instance = new AuctionHouse(adapter);
        }
        return instance;
    }

    public void setProducts(Map<Integer, Product> products) {
        this.products = products;
    }

    public void setClients(Map<Integer, Client> clients) {
        this.clients = clients;
    }

    public void setAuctions(Map<Integer, Auction> auctions) {
        this.auctions = auctions;
    }

    public void setBrokers(Map<Integer, Broker> brokers) {
        this.brokers = brokers;
    }

    public Map<Integer, Product> getProducts() {
        return products;
    }

    public Map<Integer, Client> getClients() {
        return clients;
    }

    public Map<Integer, Auction> getAuctions() {
        return auctions;
    }

    public Map<Integer, Broker> getBrokers() {
        return brokers;
    }

    public void registerClients() {
        adapter.readClient(clients);
    }

    public void registerProducts() {
        adapter.readProduct(products);
    }

    public void listClients() {
        System.out.println(clients);
    }

    public synchronized void listProducts() {
        System.out.println(products);
    }
}
