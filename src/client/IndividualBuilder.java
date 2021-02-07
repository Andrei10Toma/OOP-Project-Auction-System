package client;


public class IndividualBuilder extends AbstractClientBuilder<Individual, IndividualBuilder> {
    private String birthdate;

    public IndividualBuilder withBirthdate(String birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    @Override
    protected Individual internalBuild() {
        return new Individual(this.name, this.address, this.birthdate);
    }
}
