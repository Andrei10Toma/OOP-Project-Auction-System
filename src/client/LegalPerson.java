package client;

import java.util.Objects;

public class LegalPerson extends Client {
    private double socialCapital;
    private Company companyType;

    public LegalPerson(String name, String address, double socialCapital, Company companyType) {
        super(name, address);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LegalPerson)) return false;
        if (!super.equals(o)) return false;
        LegalPerson that = (LegalPerson) o;
        return Double.compare(that.socialCapital, socialCapital) == 0 && companyType == that.companyType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), socialCapital, companyType);
    }

    @Override
    public String toString() {
        return "LegalPerson" + super.toString() +
                ", socialCapital=" + getSocialCapital() +
                ", companyType=" + getCompanyType() + "}\n";
    }
}
