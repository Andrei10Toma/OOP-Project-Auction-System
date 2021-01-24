package main;

import auction_house.AuctionHouse;

public class Main {
    public static void main(String[] args) {
        AuctionHouse auctionHouse = AuctionHouse.getInstance();
        auctionHouse.registerClients("client_data");
    }
}
