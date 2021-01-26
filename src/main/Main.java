package main;

import auction_house.*;
import command.Tasks;
import product.Product;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IAdapter adapter = new Adapter("data");
        AuctionHouse auctionHouse = AuctionHouse.getInstance(adapter);
        Scanner scanner = new Scanner(System.in);
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
                    case DELETE_PRODUCT -> new Thread(new DeleteProduct(auctionHouse, Integer.parseInt(commandComponents[1]))).start();
                    case ADD_PRODUCT -> new Thread(new AddProduct(auctionHouse, new Product())).start();
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
