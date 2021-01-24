package employee;

import client.Client;

import java.util.ArrayList;
import java.util.List;

public class Broker implements Employee {
    List<Client> clients = new ArrayList<>();
}
