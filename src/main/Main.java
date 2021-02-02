package main;

import auction_house.*;
import command.Tasks;
import exceptions.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the JSON file where the data about products and clients is found: ");
        IAdapter adapter = new Adapter(scanner.nextLine());
        AuctionHouse auctionHouse = AuctionHouse.getInstance(adapter);
        while (true) {
            String command = scanner.nextLine();
            String[] commandComponents = command.split(" ");
            try {
                Tasks task = Tasks.valueOf(commandComponents[0].toUpperCase());
                if (task == Tasks.EXIT) {
                    break;
                }
                interpretCommand(auctionHouse, commandComponents, task);
            } catch (IllegalArgumentException | ClientNotFound | ProductNotFound | BrokerNotFound | ClientAlreadyEnroledForAuction | MaxPriceLessThanMinimumPrice e) {
                e.printStackTrace();
            }
        }
    }

    private static void interpretCommand(AuctionHouse auctionHouse, String[] commandComponents, Tasks task)
            throws ProductNotFound, ClientNotFound, BrokerNotFound, ClientAlreadyEnroledForAuction, MaxPriceLessThanMinimumPrice {
        switch (task) {
            case GENERATE_BROKERS -> auctionHouse.generateBrokers();
            case LIST_BROKERS -> auctionHouse.listBrokers();
            case LIST_CLIENTS -> auctionHouse.listClients();
            case LIST_PRODUCTS -> new Thread(new ListProducts(auctionHouse)).start();
            case LOAD_CLIENTS -> auctionHouse.registerClients();
            case LOAD_PRODUCTS -> auctionHouse.registerProducts();
            case BID -> auctionHouse.checkAuction(Integer.parseInt(commandComponents[1]),
                    Integer.parseInt(commandComponents[2]), Double.parseDouble(commandComponents[3]));
            case LIST_AUCTIONS -> auctionHouse.listAuctions();
            default -> throw new IllegalArgumentException("Command " + task + " not found.");
        }
    }
}
