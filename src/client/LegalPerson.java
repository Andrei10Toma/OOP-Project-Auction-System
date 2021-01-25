package client;

public class LegalPerson extends Client {
    private double socialCapital;
    private Company companyType;

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

    @Override
    public String toString() {
        return "LegalPerson {name=" + super.getName() +
                ", id=" + super.getId() +
                ", address=" + super.getAddress() +
                ", numberParticipation=" + super.getNumberParticipation() +
                ", numberAuctionWins=" + super.getNumberAuctionWins() +
                ", socialCapital=" + getSocialCapital() +
                ", companyType=" + getCompanyType() + "}\n";
    }
}
