package client;

import licitation_strategies.Strategy;
import licitation_strategies.StrategyFactory;

import java.util.Objects;
import java.util.Random;

/**
 * Representation of a client object.
 */
public abstract class Client {
    private int id;
    private String name;
    private String address;
    private int numberParticipation;
    private int numberAuctionWins;
    protected static int counterClients = 1;

    /**
     * Constructor for the client object.
     * @param name name of the client.
     * @param address address of the client.
     */
    protected Client(String name, String address) {
        // autoincrement id
        this.id = counterClients++;
        this.name = name;
        this.address = address;
        this.numberParticipation = 0;
        this.numberAuctionWins = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumberParticipation() {
        return numberParticipation;
    }

    public void setNumberParticipation(int numberParticipation) {
        this.numberParticipation = numberParticipation;
    }

    public int getNumberAuctionWins() {
        return numberAuctionWins;
    }

    public void setNumberAuctionWins(int numberAuctionWins) {
        this.numberAuctionWins = numberAuctionWins;
    }

    /**
     * choose the initial bid (a sum between 1 and 100).
     * @return the initial sum.
     */
    public double chooseInitialBid() {
        return new Random().nextDouble() * 100;
    }

    /**
     * calculate the bid for the current step.
     * @param lastBid max bid from last step.
     * @param maxSum max sum of the client.
     * @return the bid at the current step.
     */
    public double calculateBid(double lastBid, double maxSum) {
        StrategyFactory strategyFactory = new StrategyFactory();
        Strategy strategy = strategyFactory.getStrategy(new Random().nextInt(3));
        if (strategy.bid(lastBid) <= maxSum) {
            System.out.println("Client " + id + " applies the " + strategy + " strategy and bids " + strategy.bid(lastBid) + ".");
        }
        return strategy.bid(lastBid);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return numberParticipation == client.numberParticipation && numberAuctionWins == client.numberAuctionWins && name.equals(client.name) && address.equals(client.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, numberParticipation, numberAuctionWins);
    }

    @Override
    public String toString() {
        return "{name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", numberParticipation=" + numberParticipation +
                ", numberAuctionWins=" + numberAuctionWins;
    }
}
