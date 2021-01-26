package auction_house;

import product.Product;

public class AddProduct implements Runnable {
    private final AuctionHouse auctionHouse;
    private final Product productAdd;

    public AddProduct(AuctionHouse auctionHouse, Product productAdd) {
        this.auctionHouse = auctionHouse;
        this.productAdd = productAdd;
    }

    @Override
    public void run() {
        auctionHouse.addProduct(productAdd);
    }
}
