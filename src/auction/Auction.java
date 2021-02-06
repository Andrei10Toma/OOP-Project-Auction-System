package auction;

import auction_house.AuctionHouse;
import client.Client;
import employee.Broker;

import java.util.*;
import java.util.stream.Collectors;

public class Auction {
    public static final String AUCTION_STRING = "=========Auction ";
    public static final String ENDED = " ended=========";
    private int id;
    private int numberParticipants;
    private int actualNumberOfParticipants;
    private Client winnerClient;

    public Client getWinnerClient() {
        return winnerClient;
    }

    public void setWinnerClient(Client winnerClient) {
        this.winnerClient = winnerClient;
    }

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

    public boolean canStart() {
        return (numberParticipants == actualNumberOfParticipants);
    }

    public void startAuction(List<Broker> brokers, double minPriceOfTheProduct) {
        Map<Integer, Double> bidMap = new HashMap<>();
        int maxSteps = new Random().nextInt(6) + 5;
        System.out.println(AUCTION_STRING + id + " started=========");
        List<Broker> brokersWithClientInAuction = brokers.stream()
                .filter(broker -> broker.getClients().get(id) != null)
                .collect(Collectors.toList());
        brokersWithClientInAuction
                .forEach(broker -> broker.getClients().get(id)
                        .forEach(pair -> bidMap.put(pair.getKey().getId(), new Random().nextDouble() * 100)));
        for (int step = 0; step < maxSteps; step++) {
            System.out.println();
            brokersWithClientInAuction
                    .forEach(broker -> broker.requestClientsSum(id, bidMap));
            if (step != maxSteps - 1) {
                AuctionHouse.getInstance().calculateMaxBid(bidMap, id);
                if (AuctionHouse.getInstance().checkForEarlyWinner(bidMap, id, minPriceOfTheProduct)) {
                    return;
                }
            }
        }
        AuctionHouse.getInstance().declareTheWinnerOfTheAuction(bidMap, id, minPriceOfTheProduct);
    }

    @Override
    public String toString() {
        return "Auction{id=" + id +
                ", numberParticipants=" + numberParticipants +
                ", actualNumberOfParticipants=" + actualNumberOfParticipants +
                "}\n";
    }
}
