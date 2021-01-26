package auction_house;

public class ListProducts implements Runnable{
    private final AuctionHouse auctionHouse;

    public ListProducts(AuctionHouse auctionHouse) {
        this.auctionHouse = auctionHouse;
    }

    @Override
    public void run() {
        auctionHouse.listProducts();
    }
}
