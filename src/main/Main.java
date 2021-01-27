package main;

import auction_house.*;
import command.Tasks;

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
                switch (task) {
                    case LIST_CLIENTS -> auctionHouse.listClients();
                    case LIST_PRODUCTS -> new Thread(new ListProducts(auctionHouse)).start();
                    case LOAD_CLIENTS -> auctionHouse.registerClients();
                    case LOAD_PRODUCTS -> auctionHouse.registerProducts();
                    default -> throw new IllegalArgumentException("Command " + task + " not found.");
                }
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }
}
