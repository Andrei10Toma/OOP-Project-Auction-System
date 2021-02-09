package client;

import java.util.Objects;

public class Individual extends Client {
    private String birthDate;

    public Individual(String name, String address, String birthDate) {
        super(name, address);
        this.birthDate = birthDate;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Individual" + super.toString() +
                ", birthDate=" + birthDate + "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Individual)) return false;
        if (!super.equals(o)) return false;
        Individual that = (Individual) o;
        return birthDate.equals(that.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), birthDate);
    }
}
