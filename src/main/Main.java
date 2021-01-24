package main;

import auction_house.Adapter;
import auction_house.AuctionHouse;
import auction_house.IAdapter;

public class Main {
    public static void main(String[] args) {
        IAdapter adapter = new Adapter("data");
        AuctionHouse auctionHouse = AuctionHouse.getInstance(adapter);
        auctionHouse.registerClients();
        auctionHouse.listClients();
    }
}
