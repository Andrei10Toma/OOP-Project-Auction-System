package client;

public class Individual extends Client {
    private String birthDate;

    public Individual() {
        this.birthDate = "\0";
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Individual{" +
                "birthDate='" + birthDate + '\'' +
                '}';
    }
}
