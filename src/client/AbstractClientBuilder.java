package client;

/**
 * Generic builder for clients.
 * @param <E> something that extends client.
 * @param <T> something that extend ClientBuilder<E, T>
 */
public abstract class AbstractClientBuilder<E extends Client, T extends ClientBuilder<E, T>> implements ClientBuilder<E, T>{
    protected int id;
    protected String name;
    protected String address;
    protected int numberParticipation;
    protected int numberAuctionWins;

    /**
     * set the id.
     * @param id the id.
     * @return builder type T.
     */
    @Override
    public T withId(int id) {
        this.id = id;
        return self();
    }

    /**
     * set the name of the client.
     * @param name name of the client.
     * @return builder type T.
     */
    @Override
    public T withName(String name) {
        this.name = name;
        return self();
    }

    /**
     * set the address of the client.
     * @param address address of the client.
     * @return builder type T.
     */
    @Override
    public T withAddress(String address) {
        this.address = address;
        return self();
    }

    /**
     * set the number of participation for the client.
     * @param numberParticipation set the number participation for the clients.
     * @return builder type T.
     */
    @Override
    public T withNumberParticipation(int numberParticipation) {
        this.numberParticipation = numberParticipation;
        return self();
    }

    /**
     * sets the number of auction wins.
     * @param numberAuctionWins number of auction wins.
     * @return the builder type T
     */
    @Override
    public T withNumberAuctionWins(int numberAuctionWins) {
        this.numberAuctionWins = numberAuctionWins;
        return self();
    }

    /**
     * build the concrete type.
     * @return the concrete type.
     */
    @Override
    public E build() {
        return internalBuild();
    }

    /**
     * similar with build method.
     * @return the concrete type.
     */
    protected abstract E internalBuild();

    /**
     * return the builder of the given type T.
     * @return cast the object to concrete type T.
     */
    @SuppressWarnings("unchecked")
    private T self() {
        return (T) this;
    }
}
