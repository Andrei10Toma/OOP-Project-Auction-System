package auction;

public class Auction {
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

    public void startAuction() {
        System.out.println("Auction started");
    }

    @Override
    public String toString() {
        return "Auction{id=" + id +
                ", numberParticipants=" + numberParticipants +
                ", actualNumberOfParticipants=" + actualNumberOfParticipants +
                "}\n";
    }
}
