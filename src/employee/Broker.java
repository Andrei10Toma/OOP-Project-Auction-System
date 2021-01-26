package employee;

import client.Client;

import java.util.ArrayList;
import java.util.List;

public class Broker implements Employee {
    private List<Client> clients = new ArrayList<>();
    private int accumulatedSum;

    public Broker(List<Client> clients) {
        this.clients = clients;
        accumulatedSum = 0;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public int getAccumulatedSum() {
        return accumulatedSum;
    }

    public void setAccumulatedSum(int accumulatedSum) {
        this.accumulatedSum = accumulatedSum;
    }
}
