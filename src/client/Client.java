package client;

import licitation_strategies.Strategy;
import licitation_strategies.StrategyFactory;

import java.util.Random;

public abstract class Client {
    private int id;
    private String name;
    private String address;
    private int numberParticipation;
    private int numberAuctionWins;
    protected static int counterClients = 1;

    protected Client(String name, String address) {
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

    public double chooseInitialBid() {
        return new Random().nextDouble() * 100;
    }

    public double calculateBid(double lastBid, double maxSum) {
        StrategyFactory strategyFactory = new StrategyFactory();
        Strategy strategy = strategyFactory.getStrategy(new Random().nextInt(3));
        if (strategy.bid(lastBid) <= maxSum) {
            System.out.println("Client " + id + " applies the " + strategy + " strategy and bids " + strategy.bid(lastBid) + ".");
        }
        return strategy.bid(lastBid);
    }

    @Override
    public String toString() {
        return "{name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", numberParticipation=" + numberParticipation +
                ", numberAuctionWins=" + numberAuctionWins;
    }
}
