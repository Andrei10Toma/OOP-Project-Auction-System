package main;

import auction_house.*;
import command.Tasks;
import exceptions.*;
import product.ProductType;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AuctionHouse auctionHouse = AuctionHouse.getInstance();
        while (true) {
            String command = scanner.nextLine();
            String[] commandComponents = command.split(" ");
            try {
                Tasks task = Tasks.valueOf(commandComponents[0].toUpperCase());
                if (task == Tasks.EXIT) {
                    break;
                }
                interpretCommand(auctionHouse, commandComponents, task);
            } catch (IllegalArgumentException | ClientNotFound | ProductNotFound | BrokerNotFound
                    | ClientAlreadyEnroledForAuction | MaxPriceLessThanMinimumPrice | NotEnoughBrokers e) {
                e.printStackTrace();
            }
        }
    }

    private static void interpretCommand(AuctionHouse auctionHouse, String[] commandComponents, Tasks task)
            throws ProductNotFound, ClientNotFound, BrokerNotFound, ClientAlreadyEnroledForAuction, MaxPriceLessThanMinimumPrice, NotEnoughBrokers {
        switch (task) {
            case GENERATE_BROKERS -> auctionHouse.generateBrokers();
            case LIST_BROKERS -> auctionHouse.listBrokers();
            case LIST_CLIENTS -> auctionHouse.listClients();
            case LIST_PRODUCTS -> new Thread(new ListProducts(auctionHouse)).start();
            case LOAD_CLIENTS -> auctionHouse.registerClients(commandComponents[1]);
            case LOAD_PRODUCTS -> auctionHouse.registerProducts(commandComponents[1]);
            case BID -> auctionHouse.checkAuction(Integer.parseInt(commandComponents[1]),
                    Integer.parseInt(commandComponents[2]), Double.parseDouble(commandComponents[3]));
            case LIST_AUCTIONS -> auctionHouse.listAuctions();
            // add_product {name} {minPrice} {type} {year} {charEl1} {charEl2}
            case ADD_PRODUCT -> new Thread(new AddProduct(auctionHouse.getProducts(),
                    commandComponents[1],
                    Double.parseDouble(commandComponents[2]),
                    ProductType.valueOf(commandComponents[3]),
                    Integer.parseInt(commandComponents[4]),
                    commandComponents[5],
                    commandComponents[6])).start();
            default -> throw new IllegalArgumentException("Command " + task + " not found.");
        }
    }
}
