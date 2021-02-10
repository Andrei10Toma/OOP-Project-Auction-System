package client;

/**
 * Builder for the individual object.
 */
public class IndividualBuilder extends AbstractClientBuilder<Individual, IndividualBuilder> {
    private String birthdate;

    /**
     * set the birthdate of the individual.
     * @param birthdate birthdate of the individual.
     * @return the IndividualBuilder.
     */
    public IndividualBuilder withBirthdate(String birthdate) {
        this.birthdate = birthdate;
        return this;
    }

    /**
     * builds the Individual object.
     * @return the Individual object.
     */
    @Override
    protected Individual internalBuild() {
        return new Individual(this.name, this.address, this.birthdate);
    }
}
