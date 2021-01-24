package auction_house;

import auction.Auction;
import client.Client;
import client.IndividualBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import exceptions.DuplicateClient;
import product.Product;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class AuctionHouse {
    private List<Product> products = new ArrayList<>();
    private List<Client> clients = new ArrayList<>();
    private List<Auction> auctions = new ArrayList<>();
    private static AuctionHouse instance;

    private AuctionHouse() {
    }

    public static AuctionHouse getInstance() {
        if (instance == null) {
            instance = new AuctionHouse();
        }
        return instance;
    }

    public void addClient(Client clientAdd) {
        try {
            if (clients.stream().noneMatch(client -> client.getId() == clientAdd.getId())) {
                clients.add(clientAdd);
            } else {
                throw new DuplicateClient("Client with id " + clientAdd.getId() + " already exists.");
            }
        }
        catch (DuplicateClient e) {
            e.printStackTrace();
        }
    }

    public void registerClients(String filename) {
        Gson gson = new Gson();
        try {
            JsonObject data = gson.fromJson(new FileReader(filename), JsonObject.class);
            JsonArray clientArray = data.getAsJsonArray("Clients");
            clientArray.forEach(client -> {
                JsonObject clientData = client.getAsJsonObject();
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
