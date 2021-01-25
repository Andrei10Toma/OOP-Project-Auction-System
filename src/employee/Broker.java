package employee;

import client.Client;

import java.util.ArrayList;
import java.util.List;

public class Broker implements Employee {
    private List<Client> clients = new ArrayList<>();

    public Broker(List<Client> clients) {
        this.clients = clients;
    }
}
