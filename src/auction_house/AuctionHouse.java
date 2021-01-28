package auction_house;

import auction.Auction;
import client.*;
import employee.Broker;
import exceptions.BrokerNotFound;
import exceptions.ClientAlreadyEnroledForAuction;
import exceptions.ClientNotFound;
import exceptions.ProductNotFound;
import org.apache.commons.lang3.tuple.ImmutablePair;
import product.Product;

import java.util.*;

public class AuctionHouse {
    private Map<Integer, Product> products = Collections.synchronizedMap(new TreeMap<>());
    private Map<Integer, Client> clients = new TreeMap<>();
    private Map<Integer, Auction> auctions = new TreeMap<>();
    private List<Broker> brokers = new ArrayList<>();
    private final Random random = new Random();
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

    public void setBrokers(List<Broker> brokers) {
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

    public List<Broker> getBrokers() {
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

    public void listProducts() {
        System.out.println(products);
    }

    public void generateBrokers() {
        int minimumNumberBrokers = 2;
        int maximumNumberBrokers = 10;
        int numberOfBrokers = random.nextInt(maximumNumberBrokers - minimumNumberBrokers) + minimumNumberBrokers;
        for (int i = 0; i < numberOfBrokers; i++) {
            Broker brokerAdd = new Broker();
            brokers.add(brokerAdd);
        }
        System.out.println("Generated " + numberOfBrokers + " brokers.");
    }

    private void addAuctionForProduct(int clientId, int productId, double maxPricePaidByClient) throws BrokerNotFound {
        auctions.put(productId, new Auction(random.nextInt(clients.size() - 1) + 2, productId));
        int randomBroker = random.nextInt(brokers.size());
        if (brokers.stream().anyMatch(broker -> randomBroker + 1 == broker.getId())) {
            addClientToAuctionAssignBroker(clientId, productId, maxPricePaidByClient, randomBroker);
        }
        else {
            throw new BrokerNotFound("Broker with id " + randomBroker + " not found.");
        }
    }

    private void addClientToAuction(int clientId, int productId, double maxPricePaidByClient)
            throws ClientAlreadyEnroledForAuction, BrokerNotFound {
        if (brokers.stream()
                .filter(broker -> broker.getClients().get(productId) != null)
                .anyMatch(broker -> broker.getClients().get(productId).stream()
                        .anyMatch(pair -> pair.getKey().getId() == clientId))) {
            throw new ClientAlreadyEnroledForAuction("Client with id " + clientId + " already enrolled at auction. "
                    + productId);
        }
        int randomBroker = random.nextInt(brokers.size());
        if (brokers.stream().anyMatch(broker -> randomBroker + 1 == broker.getId())) {
            if (brokers.get(randomBroker).getClients().containsKey(productId)) {
                brokers.get(randomBroker).getClients().get(productId).add(new ImmutablePair<>(clients.get(clientId), maxPricePaidByClient));
            }
            else {
                addClientToAuctionAssignBroker(clientId, productId, maxPricePaidByClient, randomBroker);
            }
            auctions.get(productId).setActualNumberOfParticipants(auctions.get(productId).getActualNumberOfParticipants() + 1);
        }
        else {
            throw new BrokerNotFound("Broker with id " + randomBroker + " not found.");
        }
    }

    private void addClientToAuctionAssignBroker(int clientId, int productId, double maxPricePaidByClient, int randomBroker) {
        brokers.get(randomBroker).getClients().put(productId, new ArrayList<>());
        brokers.get(randomBroker).getClients().get(productId).add(new ImmutablePair<>(clients.get(clientId), maxPricePaidByClient));
    }

    public void checkAuction(int clientId, int productId, double maxPricePaidByClient)
            throws ProductNotFound, ClientNotFound, BrokerNotFound, ClientAlreadyEnroledForAuction {
        if (!products.containsKey(productId)) {
            throw new ProductNotFound("Product with id " + productId + " was not found.");
        }
        if (!clients.containsKey(clientId)) {
            throw new ClientNotFound("Client with id " + clientId + "was not found");
        }
        if (!auctions.containsKey(productId)) {
            addAuctionForProduct(clientId, productId, maxPricePaidByClient);
        }
        else {
            addClientToAuction(clientId, productId, maxPricePaidByClient);
        }
        System.out.println("Client " + clientId + " assigned successfully.");
        if (auctions.get(productId).getActualNumberOfParticipants() == auctions.get(productId).getNumberParticipants()) {
            auctions.get(productId).startAuction();
        }
    }

    public void listBrokers() {
        System.out.println(brokers);
    }

    public void listAuctions() {
        System.out.println(auctions);
    }
}
