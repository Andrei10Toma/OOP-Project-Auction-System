package main;

import auction_house.AuctionHouse;

public class Main {
    public static void main(String[] args) {
        AuctionHouse auctionHouse = AuctionHouse.getInstance();
        auctionHouse.registerClients("data");
        auctionHouse.listClients();
        auctionHouse.listProducts();
    }
}
