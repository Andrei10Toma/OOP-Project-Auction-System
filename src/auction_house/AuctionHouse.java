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

/**
 * Class representing an auction house object.
 */
public class AuctionHouse {
    public static final String CLIENT = "Client ";
    public static final String CLIENT_WITH_ID = "Client with id ";
    public static final String PAID_TOO_LESS_FOR_THE_PRODUCT = " paid too less for the product ";
    private final Map<Integer, Product> products = Collections.synchronizedMap(new TreeMap<>());
    private Map<Integer, Client> clients = new TreeMap<>();
    private Map<Integer, Auction> auctions = new TreeMap<>();
    private List<Broker> brokers = new ArrayList<>();
    private final Administrator administrator = Administrator.getInstance();
    private static AuctionHouse instance;
    private final IAdapter adapter = new JSONAdapter();

    /**
     * Auction house is singleton.
     * @return instance of the auction house.
     */
    public static AuctionHouse getInstance() {
        if (instance == null) {
            instance = new AuctionHouse();
        }
        return instance;
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

    /**
     * register the clients with the adapter.
     * @param filename file where the data is stored.
     */
    public void registerClients(String filename) {
        adapter.setFilename(filename);
        adapter.readClient(clients);
    }

    /**
     * register the product with the adapter
     * @param filename JSON file where the data il stored.
     */
    public void registerProducts(String filename) {
        adapter.setFilename(filename);
        administrator.readProducts(adapter, products);
    }

    /**
     * list the clients registered in the auction house.
     */
    public void listClients() {
        System.out.println(clients);
    }

    /**
     * list the products registered in the auction house.
     */
    public void listProducts() {
        System.out.println(products);
    }

    /**
     * generate a random number of brokers between 2 and 9
     */
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

    /**
     * creates the auction for the product and the client is assigned to a random broker.
     * @param clientId client that wants to auction for the product.
     * @param productId product that the clients will auction for.
     * @param maxPricePaidByClient max price paid by the client.
     * @throws BrokerNotFound broker not found in the map of brokers.
     */
    private void addAuctionForProduct(int clientId, int productId, double maxPricePaidByClient) throws BrokerNotFound {
        auctions.put(productId, new Auction(new Random().nextInt(clients.size() - 1) + 2, productId));
        int randomBroker = new Random().nextInt(brokers.size());
        if (brokers.stream().anyMatch(broker -> randomBroker + 1 == broker.getId())) {
            addClientToAuctionAssignBroker(clientId, productId, maxPricePaidByClient, randomBroker);
        } else {
            throw new BrokerNotFound("Broker with id " + randomBroker + " not found.");
        }
    }

    /**
     * auction for product already exists and the client is added to the auction and the client is assigned to a random broker.
     * @param clientId client that is added to the auction.
     * @param productId product that the client wants to auction for.
     * @param maxPricePaidByClient the max price paid by the client.
     * @throws ClientAlreadyEnrolledForAuction client is already enrolled for the auction.
     * @throws BrokerNotFound the broker that the client will be assigned is not found in the brokers map.
     */
    private void addClientToAuction(int clientId, int productId, double maxPricePaidByClient)
            throws ClientAlreadyEnrolledForAuction, BrokerNotFound {
        if (brokers.stream()
                .filter(broker -> broker.getClients().get(productId) != null)
                .anyMatch(broker -> broker.getClients().get(productId).stream()
                        .anyMatch(pair -> pair.getKey().getId() == clientId))) {
            throw new ClientAlreadyEnrolledForAuction(CLIENT_WITH_ID + clientId + " already enrolled at auction "
                    + productId + ".");
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

    /**
     * add client to auction and the client is assigned to a random broker.
     * @param clientId client that will be assigned to a broker.
     * @param productId product that the clients will auction for.
     * @param maxPricePaidByClient the max price paid by the client for the product.
     * @param randomBroker the broker to which the client will be assigned.
     */
    private void addClientToAuctionAssignBroker(int clientId, int productId, double maxPricePaidByClient, int randomBroker) {
        brokers.get(randomBroker).getClients().put(productId, new ArrayList<>());
        brokers.get(randomBroker).getClients().get(productId).add(new ImmutablePair<>(clients.get(clientId), maxPricePaidByClient));
    }

    /**
     * checks if the auction can start and adds the client to the auction.
     * @param clientId id of the clients that wants to participate at the auction.
     * @param productId id of the product that the clients wants to auction for.
     * @param maxPricePaidByClient the max price paid by client for the product.
     * @throws ProductNotFound the clients wants to auction for a nonexistent product.
     * @throws ClientNotFound the client is not existent in the clients map.
     * @throws BrokerNotFound the broker is not found in the brokers map.
     * @throws ClientAlreadyEnrolledForAuction the client is already participating at the auction for this product.
     * @throws MaxPriceLessThanMinimumPrice the max price paid by the client is less than the minimum price of the product.
     * @throws NotEnoughBrokers there are not enough brokers generated.
     * @throws InterruptedException from Thread.sleep().
     */
    public void checkAuction(int clientId, int productId, double maxPricePaidByClient)
            throws ProductNotFound, ClientNotFound, BrokerNotFound, ClientAlreadyEnrolledForAuction,
            MaxPriceLessThanMinimumPrice, NotEnoughBrokers, InterruptedException {
        if (brokers.size() < 2) {
            throw new NotEnoughBrokers("Not enough brokers were generated.");
        }
        if (!clients.containsKey(clientId)) {
            throw new ClientNotFound(CLIENT_WITH_ID + clientId + " was not found.");
        }
        if (!products.containsKey(productId)) {
            throw new ProductNotFound("Product with id " + productId + " was not found.");
        }
        if (maxPricePaidByClient < products.get(productId).getMinPrice()) {
            throw new MaxPriceLessThanMinimumPrice(CLIENT_WITH_ID + clientId + PAID_TOO_LESS_FOR_THE_PRODUCT +
                    productId + ".");
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

    /**
     * calculate maximum bid at each step.
     * @param bidMap the bids of the clients.
     * @param auctionId id of the auction.
     */
    public void calculateMaxBid(Map<Integer, Double> bidMap, int auctionId) {
        double maxBid = Collections.max(bidMap.values());
        brokers.stream().filter(broker -> broker.getClients().get(auctionId) != null)
                .forEach(broker -> broker.informClientsAboutMaxSum(auctionId, maxBid, bidMap));
    }

    /**
     * declares the winner of the auction. Winner client is the one with the maximum bid and the max number of auction wins.
     * @param bidMap bids of the current clients in the auction.
     * @param auctionId the id of the auction.
     * @param minPriceOfTheProduct the minimum price of the product.
     * @throws InterruptedException from Thread.sleep().
     */
    public void declareTheWinnerOfTheAuction(Map<Integer, Double> bidMap, int auctionId, double minPriceOfTheProduct) throws InterruptedException {
        double winnerBid = Double.MIN_VALUE;
        List<Broker> brokersWithClientsInAuction = brokers.stream()
                .filter(broker -> broker.getClients().get(auctionId) != null)
                .collect(Collectors.toList());
        // find out who won the auction
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
        // notify the clients
        if (winnerBid >= minPriceOfTheProduct) {
            System.out.println(CLIENT + auctions.get(auctionId).getWinnerClient().getId() +
                    " wins the auction for product " + auctionId + ".");
        } else {
            auctions.get(auctionId).setWinnerClient(null);
            System.out.println("The product will not be sold because the minimum price is " + minPriceOfTheProduct +
                    " and the maximum bid is " + winnerBid + ".");
        }
        double finalWinnerBid = winnerBid;
        // end the communication between brokers and clients
        brokersWithClientsInAuction
                .forEach(broker -> broker.updateDataAndEndCommunication(auctions.get(auctionId).getWinnerClient(), auctionId, finalWinnerBid, products));
        // remove the auction from the list of active auctions
        auctions.remove(auctionId);
        Thread.sleep(100);
        System.out.println(AUCTION_STRING + auctionId + ENDED);
    }

    /**
     * check if remained only one client in the auction.
     * @param bidMap bids of the current clients in the auction.
     * @param auctionId id of the auction.
     * @param minPrice minimum price of the product.
     * @return true if the client won the bid earlier than max steps.
     * @throws InterruptedException from Thread.sleep().
     */
    public boolean checkForEarlyWinner(Map<Integer, Double> bidMap, int auctionId, double minPrice) throws InterruptedException {
        List<Broker> brokersWithClientsInAuction = brokers.stream()
                .filter(broker -> broker.getClients().get(auctionId) != null)
                .collect(Collectors.toList());
        int clientId = new ArrayList<>(bidMap.keySet()).get(0);
        if (bidMap.size() == 1 && bidMap.get(clientId) >= minPrice) {
            System.out.println(CLIENT + clientId + " wins the auction for product " + auctionId + ".");
            brokersWithClientsInAuction
                    .forEach(broker -> broker.updateDataAndEndCommunication(clients.get(clientId), auctionId, bidMap.get(clientId), products));
            auctions.remove(auctionId);
            System.out.println(AUCTION_STRING + auctionId + ENDED);
            Thread.sleep(100);
            return true;
        }
        return false;
    }

    /**
     * list the brokers.
     */
    public void listBrokers() {
        System.out.println(brokers);
    }

    /**
     * list the active auctions.
     */
    public void listAuctions() {
        System.out.println(auctions);
    }
}
