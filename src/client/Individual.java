package client;

public class Individual extends Client {
    private String birthDate;

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
