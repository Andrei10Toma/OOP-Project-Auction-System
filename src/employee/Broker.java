package employee;

import client.Client;
import exceptions.ProductNotFound;
import product.Product;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.commons.lang3.tuple.Pair;

public class Broker implements Employee {
    private Map<Integer, List<Pair<Client, Double>>> clients = new TreeMap<>();
    private double accumulatedSum;
    private final int id;
    private static int counterBroker = 1;

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

    public void requestClientsSum(int auctionId, Map<Integer, Double> bidMap) {
        clients.get(auctionId)
                .forEach(pairClientsMaxPrice -> {
                    if (bidMap.containsKey(pairClientsMaxPrice.getKey().getId())) {
                        double bidSum = pairClientsMaxPrice.getKey().calculateBid(bidMap.get(pairClientsMaxPrice.getKey().getId()), pairClientsMaxPrice.getValue());
                        if (bidSum <= pairClientsMaxPrice.getValue()) {
                            bidMap.put(pairClientsMaxPrice.getKey().getId(), bidSum);
                        }
                        else {
                            System.out.println("Client " + pairClientsMaxPrice.getKey().getId() + " exits the auction because he can't bid.");
                            bidMap.remove(pairClientsMaxPrice.getKey().getId());
                        }
                    }
                });
    }

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

    public void updateDataAndEndCommunication(Client winner, int auctionId, double winnerBid, Map<Integer, Product> productMap) {
        CommissionFactory commissionFactory = new CommissionFactory();
        clients.get(auctionId).forEach(pairClientDouble -> {
            if (winner != null && winner.getId() == pairClientDouble.getKey().getId()) {
                winner.setNumberAuctionWins(winner.getNumberAuctionWins() + 1);
                accumulatedSum += commissionFactory.chooseCommission(winner).calculateCommission(winnerBid);
                deleteProduct(auctionId, productMap);
            }
            pairClientDouble.getKey().setNumberParticipation(pairClientDouble.getKey().getNumberParticipation() + 1);
        });
        clients.remove(auctionId);
    }

    @Override
    public void addProduct(Product product, Map<Integer, Product> productMap) {
        System.err.println("Brokers can't add products to the auction house");
    }

    @Override
    public void deleteProduct(int productId, Map<Integer, Product> productMap) {
        try {
            if (productMap.containsKey(productId)) {
                productMap.remove(productId);
            } else {
                throw new ProductNotFound("Product with " + productId + " was not found.");
            }
        } catch (ProductNotFound e) {
            e.printStackTrace();
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
