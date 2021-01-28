package auction_house;

import client.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import exceptions.DuplicateClientException;
import exceptions.DuplicateProductException;
import product.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class Adapter implements IAdapter {
    private final String filename;
    private static final String PRODUCT_TYPE = "productType";
    private static final String MIN_PRICE = "minPrice";

    public Adapter(String filename) {
        this.filename = filename;
    }

    private Client createLegalPersonClient(JsonObject clientData) {
        return new LegalPersonBuilder()
                .withAddress(clientData.get("address").getAsString())
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
                .withName(clientData.get("name").getAsString())
                .withBirthDate(clientData.get("birthdate").getAsString())
                .withNumberAuctionWins(0)
                .withNumberParticipation(0)
                .build();
    }

    public void addClient(Map<Integer, Client> clients, Client clientAdd) throws DuplicateClientException {
        if (!clients.containsKey(clientAdd.getId())) {
            clients.put(clientAdd.getId(), clientAdd);
        }
        else {
            throw new DuplicateClientException("Duplicate with id " + clientAdd.getId() + " already exists");
        }
    }

    @Override
    public void readClient(Map<Integer, Client> clients) {
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
                } catch (DuplicateClientException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("Client read was successful.");
            return;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Couldn't read the clients information.");
    }

    public void addProduct(Map<Integer, Product> products, Product productAdd) throws DuplicateProductException {
        if (!products.containsKey(productAdd.getId())) {
            products.put(productAdd.getId(), productAdd);
        }
        else {
            throw new DuplicateProductException("Product with id " + productAdd.getId() + " already exists.");
        }
    }

    private Product createFurnitureProduct(JsonObject productData) {
        return new FurnitureBuilder()
                .withName(productData.get("name").getAsString())
                .withMinPrice(productData.get(MIN_PRICE).getAsDouble())
                .withYear(productData.get("year").getAsInt())
                .withType(productData.get("type").getAsString())
                .withMaterial(productData.get("material").getAsString())
                .build();
    }

    private Product createJewelProduct(JsonObject productData) {
        return new JewelBuilder()
                .withName(productData.get("name").getAsString())
                .withMinPrice(productData.get(MIN_PRICE).getAsDouble())
                .withYear(productData.get("year").getAsInt())
                .withMaterial(productData.get("material").getAsString())
                .withGem(productData.get("gem").getAsBoolean())
                .build();
    }

    private Product createPaintingProduct(JsonObject productData) {
        return new PaintingBuilder()
                .withName(productData.get("name").getAsString())
                .withMinPrice(productData.get(MIN_PRICE).getAsDouble())
                .withYear(productData.get("year").getAsInt())
                .withColors(Colors.valueOf(productData.get("colors").getAsString()))
                .withPainterName(productData.get("painterName").getAsString())
                .build();
    }

    @Override
    public void readProduct(Map<Integer, Product> products) {
        Gson gson = new Gson();
        try {
            JsonObject data = gson.fromJson(new FileReader(filename), JsonObject.class);
            JsonArray productArray = data.getAsJsonArray("Products");
            productArray.forEach(product -> {
                JsonObject productData = product.getAsJsonObject();
                try {
                    if (ProductType.FURNITURE == ProductType.valueOf(productData.get(PRODUCT_TYPE).getAsString())) {
                        addProduct(products, createFurnitureProduct(productData));
                    }
                    else if (ProductType.JEWEL == ProductType.valueOf(productData.get(PRODUCT_TYPE).getAsString())) {
                        addProduct(products, createJewelProduct(productData));
                    }
                    else if (ProductType.PAINTING == ProductType.valueOf(productData.get(PRODUCT_TYPE).getAsString())) {
                        addProduct(products, createPaintingProduct(productData));
                    }
                } catch (DuplicateProductException e) {
                    e.printStackTrace();
                }
            });
            System.out.println("Product read was successful.");
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Couldn't read the products information.");
    }
}
