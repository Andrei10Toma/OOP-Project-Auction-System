package auction_house;

import auction.Auction;
import client.Client;
import employee.Administrator;
import employee.Broker;
import exceptions.*;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import product.Product;

import java.util.*;
import java.util.stream.Collectors;

import static auction.Auction.AUCTION_STRING;
import static auction.Auction.ENDED;

public class AuctionHouse {
    public static final String CLIENT = "Client ";
    private Map<Integer, Product> products = new TreeMap<>();
    private Map<Integer, Client> clients = new TreeMap<>();
    private Map<Integer, Auction> auctions = new TreeMap<>();
    private List<Broker> brokers = new ArrayList<>();
    private final Administrator administrator = Administrator.getInstance();
    private static AuctionHouse instance;
    private IAdapter adapter;

    public static AuctionHouse getInstance() {
        Scanner scanner = new Scanner(System.in);
        if (instance == null) {
            instance = new AuctionHouse();
            System.out.print("Enter the name of the JSON file to read the data about clients and products: ");
            instance.adapter = new JSONAdapter(scanner.next());
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
        administrator.readProducts(adapter, products);
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
        int numberOfBrokers = new Random().nextInt(maximumNumberBrokers - minimumNumberBrokers) + minimumNumberBrokers;
        for (int i = 0; i < numberOfBrokers; i++) {
            Broker brokerAdd = new Broker();
            brokers.add(brokerAdd);
        }
        System.out.println("Generated " + brokers.size() + " brokers.");
    }

    private void addAuctionForProduct(int clientId, int productId, double maxPricePaidByClient) throws BrokerNotFound, MaxPriceLessThanMinimumPrice {
        auctions.put(productId, new Auction(new Random().nextInt(clients.size() - 1) + 2, productId));
        int randomBroker = new Random().nextInt(brokers.size());
        if (brokers.stream().anyMatch(broker -> randomBroker + 1 == broker.getId())) {
            addClientToAuctionAssignBroker(clientId, productId, maxPricePaidByClient, randomBroker);
        } else {
            throw new BrokerNotFound("Broker with id " + randomBroker + " not found.");
        }
    }

    private void addClientToAuction(int clientId, int productId, double maxPricePaidByClient)
            throws ClientAlreadyEnroledForAuction, BrokerNotFound, MaxPriceLessThanMinimumPrice {
        if (brokers.stream()
                .filter(broker -> broker.getClients().get(productId) != null)
                .anyMatch(broker -> broker.getClients().get(productId).stream()
                        .anyMatch(pair -> pair.getKey().getId() == clientId))) {
            throw new ClientAlreadyEnroledForAuction("Client with id " + clientId + " already enrolled at auction. "
                    + productId);
        }
        int randomBroker = new Random().nextInt(brokers.size());
        if (brokers.stream().anyMatch(broker -> randomBroker + 1 == broker.getId())) {
            if (brokers.get(randomBroker).getClients().containsKey(productId)) {
                brokers.get(randomBroker).getClients().get(productId).add(new ImmutablePair<>(clients.get(clientId), maxPricePaidByClient));
            } else {
                addClientToAuctionAssignBroker(clientId, productId, maxPricePaidByClient, randomBroker);
            }
            auctions.get(productId).setActualNumberOfParticipants(auctions.get(productId).getActualNumberOfParticipants() + 1);
        } else {
            throw new BrokerNotFound("Broker with id " + randomBroker + " not found.");
        }
    }

    private void addClientToAuctionAssignBroker(int clientId, int productId, double maxPricePaidByClient, int randomBroker) throws MaxPriceLessThanMinimumPrice {
        if (maxPricePaidByClient < products.get(productId).getMinPrice()) {
            throw new MaxPriceLessThanMinimumPrice("Client with " + clientId + " paid too less for the product " + productId + ".");
        }
        brokers.get(randomBroker).getClients().put(productId, new ArrayList<>());
        brokers.get(randomBroker).getClients().get(productId).add(new ImmutablePair<>(clients.get(clientId), maxPricePaidByClient));
    }

    public void checkAuction(int clientId, int productId, double maxPricePaidByClient)
            throws ProductNotFound, ClientNotFound, BrokerNotFound, ClientAlreadyEnroledForAuction, MaxPriceLessThanMinimumPrice, NotEnoughBrokers {
        if (brokers.size() < 2) {
            throw new NotEnoughBrokers("Not enough brokers were generated.");
        }
        if (!products.containsKey(productId)) {
            throw new ProductNotFound("Product with id " + productId + " was not found.");
        }
        if (!clients.containsKey(clientId)) {
            throw new ClientNotFound("Client with id " + clientId + "was not found");
        }
        if (!auctions.containsKey(productId)) {
            addAuctionForProduct(clientId, productId, maxPricePaidByClient);
        } else {
            addClientToAuction(clientId, productId, maxPricePaidByClient);
        }
        System.out.println(CLIENT + clientId + " assigned successfully at auction for product " + productId + ".");
        if (auctions.get(productId).canStart()) {
            auctions.get(productId).startAuction(brokers, products.get(productId).getMinPrice());
        }
    }

    public void calculateMaxBid(Map<Integer, Double> bidMap, int auctionId) {
        double maxBid = Collections.max(bidMap.values());
        brokers.stream().filter(broker -> broker.getClients().get(auctionId) != null)
                .forEach(broker -> broker.informClientsAboutMaxSum(auctionId, maxBid, bidMap));
    }

    public void declareTheWinnerOfTheAuction(Map<Integer, Double> bidMap, int auctionId, double minPriceOfTheProduct) {
        double winnerBid = Double.MIN_VALUE;
        List<Broker> brokersWithClientsInAuction = brokers.stream()
                .filter(broker -> broker.getClients().get(auctionId) != null)
                .collect(Collectors.toList());
        for (Broker broker : brokersWithClientsInAuction) {
            for (Pair<Client, Double> pair : broker.getClients().get(auctionId)) {
                if (bidMap.containsKey(pair.getKey().getId())) {
                    if (bidMap.get(pair.getKey().getId()) > winnerBid) {
                        winnerBid = bidMap.get(pair.getKey().getId());
                        auctions.get(auctionId).setWinnerClient(pair.getKey());
                    } else if (bidMap.get(pair.getKey().getId()) == winnerBid &&
                            auctions.get(auctionId).getWinnerClient().getNumberAuctionWins() < pair.getKey().getNumberAuctionWins()) {
                        auctions.get(auctionId).setWinnerClient(pair.getKey());
                    }
                }
            }
        }
        System.out.println();
        if (winnerBid >= minPriceOfTheProduct) {
            System.out.println(CLIENT + auctions.get(auctionId).getWinnerClient().getId() +
                    " wins the auction for product " + auctionId + ".");
        } else {
            auctions.get(auctionId).setWinnerClient(null);
            System.out.println("The product will not be sold because the minimum price is " + minPriceOfTheProduct +
                    " and the maximum bid is " + winnerBid + ".");
        }
        double finalWinnerBid = winnerBid;
        brokersWithClientsInAuction
                .forEach(broker -> broker.updateDataAndEndCommunication(auctions.get(auctionId).getWinnerClient(), auctionId, finalWinnerBid, products));
        auctions.remove(auctionId);
        System.out.println(AUCTION_STRING + auctionId + ENDED);
    }

    public boolean checkForEarlyWinner(Map<Integer, Double> bidMap, int auctionId, double minPrice) {
        int clientId = new ArrayList<>(bidMap.keySet()).get(0);
        if (bidMap.size() == 1 && bidMap.get(clientId) >= minPrice) {
            System.out.println(CLIENT + clientId + " wins the auction for product " + auctionId + ".");
            System.out.println(AUCTION_STRING + auctionId + ENDED);
            return true;
        }
        return false;
    }

    public void listBrokers() {
        System.out.println(brokers);
    }

    public void listAuctions() {
        System.out.println(auctions);
    }
}
