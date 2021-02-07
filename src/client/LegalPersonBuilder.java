package client;

public class LegalPersonBuilder extends AbstractClientBuilder<LegalPerson, LegalPersonBuilder> {
    private Company companyType;
    private double socialCapital;

    public LegalPersonBuilder withCompanyType(Company companyType) {
        this.companyType = companyType;
        return this;
    }

    public LegalPersonBuilder withSocialCapital(double socialCapital) {
        this.socialCapital = socialCapital;
        return this;
    }

    @Override
    protected LegalPerson internalBuild() {
        return new LegalPerson(this.name, this.address, this.socialCapital, this.companyType);
    }
}
