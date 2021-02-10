package employee;

import auction_house.AuctionHouse;
import auction_house.DeleteProduct;
import client.Client;
import exceptions.ProductNotFound;
import product.Product;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.tuple.Pair;
import product.ProductType;

/**
 * representation of a broker object.
 */
public class Broker implements Employee {
    private Map<Integer, List<Pair<Client, Double>>> clients = new TreeMap<>();
    private double accumulatedSum;
    private final int id;
    private static int counterBroker = 1;

    /**
     * constructor for broker.
     */
    public Broker() {
        this.id = counterBroker;
        counterBroker++;
    }

    public Map<Integer, List<Pair<Client, Double>>> getClients() {
        return clients;
    }

    public int getId() {
        return id;
    }

    public void setClients(Map<Integer, List<Pair<Client, Double>>> clients) {
        this.clients = clients;
    }

    public double getAccumulatedSum() {
        return accumulatedSum;
    }

    public void setAccumulatedSum(int accumulatedSum) {
        this.accumulatedSum = accumulatedSum;
    }

    /**
     * request the initial sum from the clients.
     *
     * @param auctionId auction id.
     * @param bidMap    the current bids of the clients.
     */
    public void requestClientsInitialSum(int auctionId, Map<Integer, Double> bidMap) {
        clients.get(auctionId)
                .forEach(pairClientMaxPrice ->
                        bidMap.put(pairClientMaxPrice.getKey().getId(), pairClientMaxPrice.getKey().chooseInitialBid()));
    }

    /**
     * request the sum from the clients at each step.
     *
     * @param auctionId the id of the auction.
     * @param bidMap    the current bids of the clients.
     */
    public void requestClientsSum(int auctionId, Map<Integer, Double> bidMap) {
        clients.get(auctionId)
                .forEach(pairClientsMaxPrice -> {
                    if (bidMap.containsKey(pairClientsMaxPrice.getKey().getId())) {
                        double bidSum = pairClientsMaxPrice.getKey().calculateBid(bidMap.get(pairClientsMaxPrice.getKey().getId()), pairClientsMaxPrice.getValue());
                        if (bidSum <= pairClientsMaxPrice.getValue()) {
                            bidMap.put(pairClientsMaxPrice.getKey().getId(), bidSum);
                        } else {
                            if (bidMap.size() != 1) {
                                System.out.println("Client " + pairClientsMaxPrice.getKey().getId() + " exits the auction because he can't bid.");
                                bidMap.remove(pairClientsMaxPrice.getKey().getId());
                            }
                        }
                    }
                });
    }

    /**
     * inform the clients about the max sum to know if they exit the auction or not.
     *
     * @param auctionId the id of the auction.
     * @param maxBid    the max bid of the actual step.
     * @param bidMap    the map with the current bids of the clients.
     */
    public void informClientsAboutMaxSum(int auctionId, double maxBid, Map<Integer, Double> bidMap) {
        clients.get(auctionId)
                .forEach(pairClientMaxPrice -> {
                    if (bidMap.containsKey(pairClientMaxPrice.getKey().getId())) {
                        if (pairClientMaxPrice.getValue() >= maxBid) {
                            bidMap.put(pairClientMaxPrice.getKey().getId(), maxBid);
                        } else {
                            if (bidMap.size() != 1) {
                                System.out.println("Client " + pairClientMaxPrice.getKey().getId() + " exits the auction because max bid is bigger.");
                                bidMap.remove(pairClientMaxPrice.getKey().getId());
                            }
                        }
                    }
                });
    }

    /**
     * update the product data from the auction house and end the communication between the broker and the clients.
     *
     * @param winner     winner of the auction.
     * @param auctionId auction that just finished.
     * @param winnerBid the final bid of the winner.
     * @param productMap the product map from the auction house.
     */
    public void updateDataAndEndCommunication(Client winner, int auctionId, double winnerBid,
                                              Map<Integer, Product> productMap) {
        CommissionFactory commissionFactory = new CommissionFactory();
        clients.get(auctionId).forEach(pairClientDouble -> {
            if (winner != null && winner.getId() == pairClientDouble.getKey().getId()) {
                System.out.println("Broker " + id + " accumulates commission from client " + winner.getId());
                winner.setNumberAuctionWins(winner.getNumberAuctionWins() + 1);
                accumulatedSum += commissionFactory.chooseCommission(winner).calculateCommission(winnerBid);
                new Thread(new DeleteProduct(this, productMap, auctionId, winnerBid)).start();
            }
            pairClientDouble.getKey().setNumberParticipation(pairClientDouble.getKey().getNumberParticipation() + 1);
        });
        clients.remove(auctionId);
    }

    /**
     * brokers can't add products to the auction house.
     * @param productMap map of the products
     * @param type type of the product.
     * @param name name of the product.
     * @param minPrice minimum price of the product.
     * @param year fabrication year of the product.
     * @param elem1 first characteristic element.
     * @param elem2 second characteristic element.
     */
    @Override
    public void addProduct(Map<Integer, Product> productMap, ProductType type, String name, double minPrice, int year, String elem1, String elem2) {
        System.out.println("Brokers can't add products.");
    }

    /**
     * delete the product from the auction house when the auction ends.
     * @param productId the product that will be deleted.
     * @param productMap map of the products from the auction house.
     * @param winnerBid the winner bid for the product (sell price of the product).
     */
    @Override
    public void deleteProduct(int productId, Map<Integer, Product> productMap, double winnerBid) {
        synchronized (AuctionHouse.getInstance().getProducts()) {
            try {
                if (productMap.containsKey(productId)) {
                    productMap.get(productId).setSellPrice(winnerBid);
                    productMap.remove(productId);
                } else {
                    throw new ProductNotFound("Product with " + productId + " was not found.");
                }
            } catch (ProductNotFound e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "Broker_" + id + "{" +
                "clientsMap=" + clients +
                ", accumulatedSum=" + accumulatedSum +
                "}\n";
    }
}
