package client;

public abstract class AbstractClientBuilder<E extends Client, T extends ClientBuilder<E, T>> implements ClientBuilder<E, T>{
    protected int id;
    protected String name;
    protected String address;
    protected int numberParticipation;
    protected int numberAuctionWins;

    @Override
    public T withId(int id) {
        this.id = id;
        return self();
    }

    @Override
    public T withName(String name) {
        this.name = name;
        return self();
    }

    @Override
    public T withAddress(String address) {
        this.address = address;
        return self();
    }

    @Override
    public T withNumberParticipation(int numberParticipation) {
        this.numberParticipation = numberParticipation;
        return self();
    }

    @Override
    public T withNumberAuctionWins(int numberAuctionWins) {
        this.numberAuctionWins = numberAuctionWins;
        return self();
    }

    @Override
    public E build() {
        return internalBuild();
    }

    protected abstract E internalBuild();

    @SuppressWarnings("unchecked")
    private T self() {
        return (T) this;
    }
}
