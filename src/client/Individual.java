package client;

public class Individual extends Client {
    private String birthDate;
//
//    public Individual(int id, String name, String address, String birthDate) {
//        super(id, name, address);
//        this.birthDate = birthDate;
//    }

    public Individual() {

    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
