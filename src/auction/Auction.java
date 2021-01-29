package auction;

import auction_house.Adapter;
import auction_house.AuctionHouse;
import employee.Broker;
import licitation_strategies.Strategy;
import licitation_strategies.StrategyFactory;

import java.util.*;

public class Auction {
    private static final String CLIENT = "Client ";
    private int id;
    private int numberParticipants;
    private int actualNumberOfParticipants;

    public Auction(int numberParticipants, int id) {
        this.numberParticipants = numberParticipants;
        this.id = id;
        actualNumberOfParticipants = 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumberParticipants() {
        return numberParticipants;
    }

    public void setNumberParticipants(int numberParticipants) {
        this.numberParticipants = numberParticipants;
    }

    public int getActualNumberOfParticipants() {
        return actualNumberOfParticipants;
    }

    public void setActualNumberOfParticipants(int actualNumberOfParticipants) {
        this.actualNumberOfParticipants = actualNumberOfParticipants;
    }

    public void startAuction(List<Broker> brokers, double minPriceOfTheProduct) {
        StrategyFactory strategyFactory = new StrategyFactory();
        System.out.println("=========Auction " + id + " started=========");
        int maxSteps = new Random().nextInt(6) + 5;
        Map<Integer, Double> bidMap = new HashMap<>();
        Map<Integer, Strategy> strategyMap = new HashMap<>();
        Map<Integer, Double> maxPricesMap = new HashMap<>();
        brokers.stream()
                .filter(broker -> broker.getClients().get(id) != null)
                .forEach(broker -> broker.getClients().get(id)
                        .forEach(pairClientMaxSum -> {
                            bidMap.put(pairClientMaxSum.getKey().getId(), minPriceOfTheProduct);
                            strategyMap.put(pairClientMaxSum.getKey().getId(), null);
                            maxPricesMap.put(pairClientMaxSum.getKey().getId(), pairClientMaxSum.getValue());
                            pairClientMaxSum.getKey().setNumberParticipation(pairClientMaxSum.getKey().getNumberParticipation() + 1);
                        }));
        for (int step = 0; step < maxSteps; step++) {
            System.out.println(bidMap);
            strategyMap.forEach((client, strategy) -> {
                strategy = strategyFactory.getStrategy(new Random().nextInt(3));
                strategyMap.put(client, strategy);
                System.out.println(CLIENT + client + " applies the " + strategy + " strategy.");
            });
            bidMap.forEach((client, price) -> {
                price = strategyMap.get(client).bid(price);
                if (price > maxPricesMap.get(client)) {
                    strategyMap.remove(client);
                    maxPricesMap.remove(client);
                    bidMap.remove(client);
                    System.out.println(CLIENT + client + " exits the auction.");
                }
                else {
                    bidMap.put(client, price);
                    System.out.println(CLIENT + client + " bids " + price + ".");
                }
            });
            // TODO: declare the winner of the auction and look if the price goes above the maximum price given by the client
            double max = AuctionHouse.getInstance(new Adapter("data")).calculateMaxBid(bidMap);
            bidMap.forEach((client, price) -> bidMap.put(client, max));
            System.out.println();
        }
        System.out.println("=========Auction " + id + " finished=========");
    }

    @Override
    public String toString() {
        return "Auction{id=" + id +
                ", numberParticipants=" + numberParticipants +
                ", actualNumberOfParticipants=" + actualNumberOfParticipants +
                "}\n";
    }
}
