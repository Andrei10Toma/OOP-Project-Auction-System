package client;

import java.util.Objects;

/**
 * Representation of an individual object.
 */
public class Individual extends Client {
    private String birthDate;

    /**
     * Constructor for the individual client object.
     * @param name name of the client.
     * @param address address of the client.
     * @param birthDate birth date of the client.
     */
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
