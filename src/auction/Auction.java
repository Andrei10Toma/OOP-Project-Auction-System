package auction;

import auction_house.Adapter;
import auction_house.AuctionHouse;
import employee.Broker;
import exceptions.MaxPriceLessThanMinimumPrice;
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

    /*TODO: look on this code again, seems a fucking mess; declare the winner, end the communication between brokers and clients that
     *  participated at the auction, remove the auction from the auction map, update the field of the product with the sell price,
     * increment the number of auction wins, take a paper and a pen and take it step by step to see whats happening.*/
    public void startAuction(List<Broker> brokers, double minPriceOfTheProduct) {
        Map<Integer, Double> bidMap = new HashMap<>();
        Map<Integer, Strategy> strategyMap = new HashMap<>();
        Map<Integer, Double> maxPricesMap = new HashMap<>();
        StrategyFactory strategyFactory = new StrategyFactory();
        System.out.println("=========Auction " + id + " started=========");
        int maxSteps = new Random().nextInt(6) + 5;
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

        }
        /*for (int step = 0; step < maxSteps; step++) {
            if (bidMap.size() == 1) {
                bidMap.forEach((client, product) -> System.out.println(CLIENT + client + " won the auction."));
                break;
            }
            System.out.println(bidMap);
            bidMap.forEach((client, price) -> {
                if (strategyMap.get(client).bid(price) <= maxPricesMap.get(client)) {
                    price = strategyMap.get(client).bid(price);
                    bidMap.put(client, price);
                    System.out.println(CLIENT + client + " bids " + price + ".");
                } else {
                    System.out.println(CLIENT + client + " exits the auction.");
                    bidMap.remove(client);
                    strategyMap.remove(client);
                    maxPricesMap.remove(client);
                }
            });
            double max = AuctionHouse.getInstance(new Adapter("data")).calculateMaxBid(bidMap);
            bidMap.forEach((client, price) -> {
                if (maxPricesMap.get(client) <= max) {
                    System.out.println(CLIENT + client + " exits the auction.");
                    bidMap.remove(client);
                    strategyMap.remove(client);
                    maxPricesMap.remove(client);
                } else {
                    bidMap.put(client, max);
                }
            });
            System.out.println();
        }
        System.out.println("=========Auction " + id + " finished=========");*/
    }

    private void getBestStrategy(StrategyFactory strategyFactory, Map<Integer, Strategy> strategyMap) {
        strategyMap.forEach((client, strategy) -> {
            strategy = strategyFactory.getStrategy(new Random().nextInt(3));
            strategyMap.put(client, strategy);
            System.out.println(CLIENT + client + " applies the " + strategy + " strategy.");
        });
    }

    @Override
    public String toString() {
        return "Auction{id=" + id +
                ", numberParticipants=" + numberParticipants +
                ", actualNumberOfParticipants=" + actualNumberOfParticipants +
                "}\n";
    }
}
