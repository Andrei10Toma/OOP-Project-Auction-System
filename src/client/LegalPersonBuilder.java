package client;

public class LegalPersonBuilder implements ClientBuilder<LegalPerson, LegalPersonBuilder>{
    LegalPerson legalPerson = new LegalPerson();

    @Override
    public LegalPersonBuilder withId(int id) {
        legalPerson.setId(id);
        return this;
    }

    @Override
    public LegalPersonBuilder withName(String name) {
        legalPerson.setName(name);
        return this;
    }

    @Override
    public LegalPersonBuilder withAddress(String address) {
        legalPerson.setAddress(address);
        return this;
    }

    @Override
    public LegalPersonBuilder withNumberParticipation(int numberParticipation) {
        legalPerson.setNumberParticipation(numberParticipation);
        return this;
    }

    @Override
    public LegalPersonBuilder withNumberAuctionWins(int numberAuctionWins) {
        legalPerson.setNumberAuctionWins(numberAuctionWins);
        return this;
    }

    @Override
    public LegalPerson build() {
        legalPerson.setId(Client.counterClients);
        Client.updateCounter();
        return legalPerson;
    }

    public LegalPersonBuilder withSocialCapital(double socialCapital) {
        legalPerson.setSocialCapital(socialCapital);
        return this;
    }

    public LegalPersonBuilder withCompanyType(Company companyType) {
        legalPerson.setCompanyType(companyType);
        return this;
    }
}
