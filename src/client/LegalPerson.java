package client;

import java.util.Objects;

/**
 * representation of a Legal Client object.
 */
public class LegalPerson extends Client {
    private double socialCapital;
    private Company companyType;

    /**
     * Constructor for the legal client object.
     * @param name name of the client.
     * @param address address of the client.
     * @param socialCapital social capital of the legal client.
     * @param companyType company type of the legal person client.
     */
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
