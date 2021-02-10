# OOP-Project-Auction-System

## Input and commands

The data about clients and products are read from a JSON file, and the extracted data is
stored in two maps from the auction house: productMap and clientsMap. Further, the program
will run through commands from standard input. The maps from the auction house (clients, auctions,
products) will contain an Integer which is the id (autoincrement) of every element and a 
reference to the object. The auctions will have the same id as the productId.

### Commands:
    load_products {filename} - load the products information from the given JSON file;
    load_clients {filename} - load the clients information from the given JSON file;
    generate_brokers - generates a random number of brokers between 2 and 9;
    list_products - list the products from the auction house;
    list_clients - list the clients from the auction house;
    list_brokers - list the brokers from the auction house;
    add_product {name} {minPrice} {productType} {year} {characteristicElement1} {characteristicElement2} - adds the
    product to the products map.
    wait - the main thread will wait 0.1 seconds
    bid {clientId} {productId} {maxPricePaid} - client bids for the product and will bid unitl a max price.

## Auction System
The client sends a request to the auction house with the bid command. After some verifications
to see if the client and product exists in the auction house, if the brokers were generated and
if the max price that is paid by the client is bigger than the minimum price of the product.
Further, the auction is added to the auctions map from the auction house if it doesn't exist
already, the client is added to the auction and assigned to a random broker. After this steps,
it is checked if the auction can start. If the auction can start (numberOfParticipants == numberOfClientsEnrolled),
all the brokers will come for the auction, and they will ask every client for an initial sum.
A number of maxSteps will be randomly generated, and the auction will start. At every step,
the brokers that have clients participating at the auction will ask their clients for a sum.
If the sum the client wants to bid is bigger than the maximum price, then the client will exit
the auction. After every client chooses the sum they want to bid, the auction house will calculate
the max of the current step and then the brokers will inform the clients about the max sum from the step.
If the max sum from the current step is bigger than the maximum price, then the client will exit
the auction. If in the auction remains only one client, the auction is won by that client if his bid is bigger
than the minimum price of the product. After the maximum number of steps the auction house
declares the winner of the auction, and the clients are informed by brokers about the result. If the max bid
is bigger than the minimum price of the product then the communication between brokers and clients
will end, the brokers will remove the product that was sold from the auction house, and the auction is removed from auction map.
If the max bid is not bigger than the minimum price of the product then the product is not deleted from
the product map.

## Design patterns

* **Adapter** - for the read from the JSON file.
* **Generic Builder** - builder for the client and product.
* **Singleton** - for the auction house and administrator.
* **Strategy** - bid strategies and applied commissions.
* **Factory** - create the bid strategy, and the applied commission.

## Multithreading Part

The multithreading part is for the product list. Administrator, brokers and clients can access
it simultaneously. I used the synchronization keyword, and the products map is a synchronized map. So that
the threads can finish I created a command wait that will sleep the main thread for 0.1 seconds.