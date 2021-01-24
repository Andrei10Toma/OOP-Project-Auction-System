package client;


public class IndividualBuilder implements ClientBuilder {
    Individual individual = new Individual();

    @Override
    public ClientBuilder withId(int id) {
        individual.setId(id);
        return this;
    }

    @Override
    public ClientBuilder withName(String name) {
        individual.setName(name);
        return this;
    }

    @Override
    public ClientBuilder withAddress(String address) {
        individual.setAddress(address);
        return this;
    }

    @Override
    public ClientBuilder withNumberParticipation(int numberParticipation) {
        individual.setNumberParticipation(numberParticipation);
        return this;
    }

    @Override
    public ClientBuilder withNumberAuctionWins(int numberAuctionWins) {
        individual.setNumberAuctionWins(numberAuctionWins);
        return this;
    }

    public ClientBuilder withBirthday(String birthday) {
        individual.setBirthDate(birthday);
        return this;
    }

    public Individual build() {
        return individual;
    }
}
