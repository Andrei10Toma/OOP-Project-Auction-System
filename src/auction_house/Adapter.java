package auction_house;

import client.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import exceptions.DuplicateClient;
import product.Product;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Adapter implements IAdapter {
    String filename;

    public Adapter(String filename) {
        this.filename = filename;
    }

    private Client createLegalPersonClient(JsonObject clientData) {
        return new LegalPersonBuilder()
                .withAddress(clientData.get("address").getAsString())
                .withId(clientData.get("id").getAsInt())
                .withName(clientData.get("name").getAsString())
                .withCompanyType(Company.valueOf(clientData.get("company").getAsString()))
                .withSocialCapital(clientData.get("socialCapital").getAsDouble())
                .withNumberAuctionWins(0)
                .withNumberParticipation(0)
                .build();
    }

    private Client createIndividualClient(JsonObject clientData) {
        return new IndividualBuilder()
                .withAddress(clientData.get("address").getAsString())
                .withId(clientData.get("id").getAsInt())
                .withName(clientData.get("name").getAsString())
                .withBirthDate(clientData.get("birthdate").getAsString())
                .withNumberAuctionWins(0)
                .withNumberParticipation(0)
                .build();
    }

    public void addClient(List<Client> clients, Client clientAdd) throws DuplicateClient {
        if (clients.stream().noneMatch(client -> client.getId() == clientAdd.getId())) {
            clients.add(clientAdd);
        }
        else {
            throw new DuplicateClient("Duplicate with id " + clientAdd.getId() + " already exists");
        }
    }

    @Override
    public List<Client> readClient() {
        List<Client> clients = new ArrayList<>();
        Gson gson = new Gson();
        try {
            JsonObject data = gson.fromJson(new FileReader(filename), JsonObject.class);
            JsonArray clientArray = data.getAsJsonArray("Clients");
            clientArray.forEach(client -> {
                JsonObject clientData = client.getAsJsonObject();
                try {
                    if (ClientType.INDIVIDUAL == ClientType.valueOf(clientData.get("type").getAsString())) {
                        addClient(clients, createIndividualClient(clientData));
                    } else {
                        addClient(clients, createLegalPersonClient(clientData));
                    }
                }catch (DuplicateClient e) {
                    e.printStackTrace();
                }
            });
            return clients;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Product> readProduct(String filename) {
        return new ArrayList<>();
    }
}
