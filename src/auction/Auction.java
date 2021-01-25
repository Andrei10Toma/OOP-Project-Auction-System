package auction;

public class Auction {
    private static int auctionsCounter = 1;
    private int id;
    private int numberParticipants;

    public Auction(int numberParticipants) {
        this.numberParticipants = numberParticipants;
        this.id = auctionsCounter++;
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
}
