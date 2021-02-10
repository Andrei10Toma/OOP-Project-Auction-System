package auction;

import auction_house.AuctionHouse;
import client.Client;
import employee.Broker;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class representing an auction object.
 */
public class Auction {
    public static final String AUCTION_STRING = "=========Auction ";
    public static final String ENDED = " ended=========";
    private int id;
    private int numberParticipants;
    private int actualNumberOfParticipants;
    private Client winnerClient;

    /**
     * Getter for the winner client of the auction.
     * @return winner client of the auction.
     */
    public Client getWinnerClient() {
        return winnerClient;
    }

    /**
     * Setter for the winner client of the auction.
     * @param winnerClient the winner client of the auction.
     */
    public void setWinnerClient(Client winnerClient) {
        this.winnerClient = winnerClient;
    }

    /**
     * Constructor for auction.
     * @param numberParticipants number of participants at the auction.
     * @param id id of the auction (same with id of the product).
     */
    public Auction(int numberParticipants, int id) {
        this.numberParticipants = numberParticipants;
        this.id = id;
        actualNumberOfParticipants = 1;
    }

    /**
     * getter for id of the auction.
     * @return id of the auction.
     */
    public int getId() {
        return id;
    }

    /**
     * setter for id of the auction.
     * @param id id of the auction.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * getter for the number of participants at the auction.
     * @return number of the participants at the auction.
     */
    public int getNumberParticipants() {
        return numberParticipants;
    }

    /**
     * setter for the number of the participation.
     * @param numberParticipants number of the participants at the auction.
     */
    public void setNumberParticipants(int numberParticipants) {
        this.numberParticipants = numberParticipants;
    }

    /**
     * getter for the number of clients enrolled at the auction.
     * @return number of clients enrolled at auction.
     */
    public int getActualNumberOfParticipants() {
        return actualNumberOfParticipants;
    }

    /**
     * setter for the number of clients enrolled at the auction.
     * @param actualNumberOfParticipants number of clients enrolled of the auction.
     */
    public void setActualNumberOfParticipants(int actualNumberOfParticipants) {
        this.actualNumberOfParticipants = actualNumberOfParticipants;
    }

    /**
     * check if the auction can start (number of participants == number of clients already enrolled).
     * @return true if the auction can start, else false.
     */
    public boolean canStart() {
        return (numberParticipants == actualNumberOfParticipants);
    }

    /**
     * auction begins when the auction can start.
     * @param brokers brokers of the auction house.
     * @param minPriceOfTheProduct minimum price of the product that the clients auction for.
     * @throws InterruptedException exception for Thread.sleep()
     */
    public void startAuction(List<Broker> brokers, double minPriceOfTheProduct) throws InterruptedException {
        // map for the bids of the clients still enrolled at auction
        Map<Integer, Double> bidMap = new HashMap<>();
        // generate a random number of max steps
        int maxSteps = new Random().nextInt(6) + 5;
        System.out.println(AUCTION_STRING + id + " started=========");
        // extract the brokers that has the clients in auction
        List<Broker> brokersWithClientInAuction = brokers.stream()
                .filter(broker -> broker.getClients().get(id) != null)
                .collect(Collectors.toList());
        // requesting the initial sum of the clients
        brokersWithClientInAuction
                .forEach(broker -> broker.requestClientsInitialSum(id, bidMap));
        for (int step = 0; step < maxSteps; step++) {
            System.out.println();
            // request sum of the client
            brokersWithClientInAuction
                    .forEach(broker -> broker.requestClientsSum(id, bidMap));
            if (step != maxSteps - 1) {
                AuctionHouse.getInstance().calculateMaxBid(bidMap, id);
                if (AuctionHouse.getInstance().checkForEarlyWinner(bidMap, id, minPriceOfTheProduct)) {
                    return;
                }
            }
        }
        // declare the winner of the auction
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
