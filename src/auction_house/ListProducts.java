package auction_house;

/**
 * class for the list products command.
 */
public class ListProducts implements Runnable {
    private final AuctionHouse auctionHouse;

    /**
     * constructor for the listProducts object.
     * @param auctionHouse the auction house.
     */
    public ListProducts(AuctionHouse auctionHouse) {
        this.auctionHouse = auctionHouse;
    }

    /**
     * run method from the implementation of Runnable.
     */
    @Override
    public void run() {
        synchronized (auctionHouse.getProducts()) {
            auctionHouse.listProducts();
        }
    }
}
