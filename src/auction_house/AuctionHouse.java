package auction_house;

import auction.Auction;
import client.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import exceptions.DuplicateClient;
import product.FurnitureBuilder;
import product.Product;
import product.ProductType;

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
        } catch (DuplicateClient e) {
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
                if (ClientType.INDIVIDUAL == ClientType.valueOf(clientData.get("type").getAsString())) {
                    addClient(new IndividualBuilder()
                            .withAddress(clientData.get("address").getAsString())
                            .withId(clientData.get("id").getAsInt())
                            .withName(clientData.get("name").getAsString())
                            .withBirthDate(clientData.get("birthdate").getAsString())
                            .withNumberAuctionWins(0)
                            .withNumberParticipation(0)
                            .build());
                } else {
                    addClient(new LegalPersonBuilder()
                            .withAddress(clientData.get("address").getAsString())
                            .withId(clientData.get("id").getAsInt())
                            .withName(clientData.get("name").getAsString())
                            .withCompanyType(Company.valueOf(clientData.get("company").getAsString()))
                            .withSocialCapital(clientData.get("socialCapital").getAsDouble())
                            .withNumberAuctionWins(0)
                            .withNumberParticipation(0)
                            .build());
                }
            });
            JsonArray productsArray = data.getAsJsonArray("Products");
            productsArray.forEach(product -> {
                JsonObject productData = product.getAsJsonObject();
                if (ProductType.valueOf(productData.get("productType").getAsString()) == ProductType.FURNITURE) {
                    products.add(new FurnitureBuilder()
                            .withId(productData.get("id").getAsInt())
                            .withName(productData.get("name").getAsString())
                            .withMinPrice(productData.get("minPrice").getAsDouble())
                            .withMaterial(productData.get("material").getAsString())
                            .withType(productData.get("type").getAsString())
                            .build());
                }
            });
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void listClients() {
        System.out.println(clients);
    }

    public void listProducts() {
        System.out.println(products);
    }
}
