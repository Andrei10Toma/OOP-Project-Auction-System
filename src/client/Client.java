package client;

public class Client {
    private int id;
    private String name;
    private String address;
    private int numberParticipation;
    private int numberAuctionWins;

    public Client(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.numberParticipation = 0;
        this.numberAuctionWins = 0;
    }

    public Client(){

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
}
