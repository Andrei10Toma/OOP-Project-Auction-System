package client;

public class LegalPerson extends Client {
    private double socialCapital;
    private Company companyType;

//    public LegalPerson(int id, String name, String address, double socialCapital, Company companyType) {
//        super(id, name, address);
//        this.socialCapital = socialCapital;
//        this.companyType = companyType;
//    }


    public LegalPerson(double socialCapital, Company companyType) {
        this.socialCapital = socialCapital;
        this.companyType = companyType;
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
