package client;


public class IndividualBuilder implements ClientBuilder<Individual, IndividualBuilder> {
    Individual individual = new Individual();

    @Override
    public IndividualBuilder withId(int id) {
        individual.setId(id);
        return this;
    }

    @Override
    public IndividualBuilder withName(String name) {
        individual.setName(name);
        return this;
    }

    @Override
    public IndividualBuilder withAddress(String address) {
        individual.setAddress(address);
        return this;
    }

    @Override
    public IndividualBuilder withNumberParticipation(int numberParticipation) {
        individual.setNumberParticipation(numberParticipation);
        return this;
    }

    @Override
    public IndividualBuilder withNumberAuctionWins(int numberAuctionWins) {
        individual.setNumberAuctionWins(numberAuctionWins);
        return this;
    }

    @Override
    public Individual build() {
        individual.setId(Client.counterClients);
        Client.updateCounter();
        return individual;
    }

    public IndividualBuilder withBirthDate(String birthDate) {
        individual.setBirthDate(birthDate);
        return this;
    }
}
