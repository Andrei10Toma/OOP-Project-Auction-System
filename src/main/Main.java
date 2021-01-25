package main;

import auction_house.Adapter;
import auction_house.AuctionHouse;
import auction_house.IAdapter;
import command.Tasks;
import exceptions.CommandNotFound;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IAdapter adapter = new Adapter("data");
        AuctionHouse auctionHouse = AuctionHouse.getInstance(adapter);
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String command = scanner.nextLine();
            String[] commandComponents = command.split(" ");
            Tasks task = Tasks.valueOf(commandComponents[0]);
            if (task == Tasks.EXIT) {
                break;
            }
            try {
                switch (task) {
                    case LIST_CLIENTS -> auctionHouse.listClients();
                    case LIST_PRODUCTS -> auctionHouse.listProducts();
                    default -> throw new CommandNotFound("Command " + task + " not found.");
                }
            }
            catch (CommandNotFound e) {
                e.printStackTrace();
            }
        }
    }
}
