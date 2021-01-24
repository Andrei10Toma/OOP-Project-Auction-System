package client;

public interface ClientBuilder<E, T extends ClientBuilder<E, T>> {
    T withId(int id);
    T withName(String name);
    T withAddress(String address);
    T withNumberParticipation(int numberParticipation);
    T withNumberAuctionWins(int numberAuctionWins);
    E build();
}
