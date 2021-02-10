package client;

/**
 * builder for a legal person object.
 */
public class LegalPersonBuilder extends AbstractClientBuilder<LegalPerson, LegalPersonBuilder> {
    private Company companyType;
    private double socialCapital;

    /**
     * set the company type of the legal person client.
     * @param companyType type of the company.
     * @return the LegalPersonBuilder.
     */
    public LegalPersonBuilder withCompanyType(Company companyType) {
        this.companyType = companyType;
        return this;
    }

    /**
     * set the social capital of the legal person client.
     * @param socialCapital social capital of the legal person client.
     * @return the LegalPersonBuilder.
     */
    public LegalPersonBuilder withSocialCapital(double socialCapital) {
        this.socialCapital = socialCapital;
        return this;
    }

    /**
     * build the LegalPerson object.
     * @return the LegalPersson object.
     */
    @Override
    protected LegalPerson internalBuild() {
        return new LegalPerson(this.name, this.address, this.socialCapital, this.companyType);
    }
}
