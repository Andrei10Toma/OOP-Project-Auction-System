package auction_house;

public class DeleteProduct implements Runnable {
    private final AuctionHouse auctionHouse;
    private final int id;

    public DeleteProduct(AuctionHouse auctionHouse, int id) {
        this.auctionHouse = auctionHouse;
        this.id = id;
    }

    @Override
    public void run() {
        auctionHouse.deleteProduct(id);
    }
}
