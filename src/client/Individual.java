package client;

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
}
