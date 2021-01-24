package client;

public class LegalPerson extends Client {
    private double socialCapital;
    private Company companyType;

    public LegalPerson() {

    }

    public double getSocialCapital() {
        return socialCapital;
    }

    public void setSocialCapital(double socialCapital) {
        this.socialCapital = socialCapital;
    }

    public Company getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Company companyType) {
        this.companyType = companyType;
    }
}
