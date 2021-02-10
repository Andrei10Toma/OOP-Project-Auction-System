package client;

/**
 * interface for the client builder
 * @param <E> type that extends Client.
 * @param <T> type that extends ClientBuilder<E, T>
 */
public interface ClientBuilder<E extends Client, T extends ClientBuilder<E, T>> {
    T withId(int id);
    T withName(String name);
    T withAddress(String address);
    T withNumberParticipation(int numberParticipation);
    T withNumberAuctionWins(int numberAuctionWins);
    E build();
}
