package testing;

import auction.Auction;
import auction_house.AuctionHouse;
import client.*;
import employee.Broker;
import exceptions.*;
import org.junit.jupiter.api.*;
import product.*;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuctionHouseTest {
    public static final String BIRTHDATE = "2000.01.01";
    private AuctionHouse auctionHouse;

    @BeforeEach
    public void setUp() {
        auctionHouse = AuctionHouse.getInstance();
    }

    @Test
    @DisplayName("Test Load Clients")
    @Order(1)
    public void testLoadClients() {
        auctionHouse.registerClients("dataTest.json");
        Map<Integer, Client> expectedClientMap = new TreeMap<>();
        expectedClientMap.put(1, new IndividualBuilder()
                .withName("Andrei Toma")
                .withAddress("andrei.toma1009@stud.acs.upb.ro")
                .withBirthdate(BIRTHDATE)
                .build());
        expectedClientMap.put(2, new IndividualBuilder()
                .withName("Stan Lucian Mihai")
                .withAddress("stanlucimihai@gmail.com")
                .withBirthdate(BIRTHDATE)
                .build());
        expectedClientMap.put(3, new LegalPersonBuilder()
                .withName("Dragomir Constantin")
                .withAddress("cos33@yahoo.com")
                .withCompanyType(Company.SRL)
                .withSocialCapital(1000)
                .build());
        expectedClientMap.put(4, new IndividualBuilder()
                .withName("Ilie Alexandru")
                .withAddress("alex_fcp@gmail.com")
                .withBirthdate(BIRTHDATE)
                .build());
        assertEquals(expectedClientMap, auctionHouse.getClients());
    }

    @Test
    @DisplayName("Test Load Products")
    @Order(2)
    public void testLoadProducts() {
        auctionHouse.registerProducts("dataTest.json");
        Map<Integer, Product> expectedProductMap = new TreeMap<>();
        expectedProductMap.put(1, new PaintingBuilder()
                .withName("Starry Night")
                .withMinPrice(3000)
                .withYear(1882)
                .withPainterName("Van Gogh")
                .withColors(Colors.OIL)
                .build());
        expectedProductMap.put(2, new JewelBuilder()
                .withName("Boys Police Necklace")
                .withMinPrice(200)
                .withYear(2020)
                .withMaterial("Stainless Steel")
                .withGem(false)
                .build());
        expectedProductMap.put(3, new FurnitureBuilder()
                .withName("Active Tennis Table")
                .withMinPrice(650)
                .withYear(2017)
                .withType("Entertainment")
                .withMaterial("Steel and Wood")
                .build());
        expectedProductMap.put(4, new PaintingBuilder()
                .withName("Mona Lisa")
                .withMinPrice(2000)
                .withYear(1870)
                .withPainterName("Leonardo DaVinci")
                .withColors(Colors.TEMPERA)
                .build());
        expectedProductMap.put(5, new JewelBuilder()
                .withName("Ring Pandora")
                .withMinPrice(300)
                .withYear(2017)
                .withMaterial("Silver")
                .withGem(true)
                .build());
        assertEquals(auctionHouse.getProducts(), expectedProductMap);
    }

    @Test
    @DisplayName("Test Generate Brokers")
    @Order(3)
    public void testGenerateBrokers() {
        auctionHouse.generateBrokers();
        assertTrue(auctionHouse.getBrokers().size() >= 2);
    }

    @Test
    @DisplayName("Test Exception Nonexistent Client wants to bid")
    @Order(4)
    public void testExceptionNonexistentClientWantsToBid() {
        Exception exception = assertThrows(ClientNotFound.class, () -> auctionHouse.checkAuction(20, 1, 2000));
        assertEquals(exception.getMessage(), "Client with id 20 was not found.");
    }

    @Test
    @DisplayName("Test no one wins the auction.")
    @Order(5)
    public void testNoOneWinsTheAuction()
            throws ClientAlreadyEnrolledForAuction, ProductNotFound, NotEnoughBrokers, ClientNotFound, MaxPriceLessThanMinimumPrice, BrokerNotFound {

        auctionHouse.getAuctions().put(1, new Auction(4, 1));
        auctionHouse.checkAuction(1, 1, 3100);
        auctionHouse.checkAuction(2, 1, 3200);
        auctionHouse.checkAuction(3, 1, 3150);
        assertTrue(auctionHouse.getProducts().containsKey(1));
        assertFalse(auctionHouse.getAuctions().containsKey(1));
    }

    @Test
    @DisplayName("Test no one wins the auction V2.")
    @Order(6)
    public void testNoOneWinsTheAuction2()
            throws ClientAlreadyEnrolledForAuction, ProductNotFound, NotEnoughBrokers, ClientNotFound, MaxPriceLessThanMinimumPrice, BrokerNotFound {
        auctionHouse.getAuctions().put(4, new Auction(4, 4));
        auctionHouse.checkAuction(1, 4, 2199);
        auctionHouse.checkAuction(2, 4, 2200);
        auctionHouse.checkAuction(4, 4, 2500);
        assertTrue(auctionHouse.getProducts().containsKey(4));
        assertFalse(auctionHouse.getAuctions().containsKey(4));
        auctionHouse.getBrokers().forEach(broker -> assertTrue(broker.getClients().keySet().stream().noneMatch(key -> key != 4)));
        assertFalse(auctionHouse.getSoldProducts().containsKey(4));
    }

    @Test
    @DisplayName("Test Exception ClientAlreadyEnrolledForAuction And MaxPriceLessThanMinimumPrice")
    @Order(7)
    public void testClientAlreadyEnrolledForAuctionException()
            throws ClientAlreadyEnrolledForAuction, ProductNotFound, NotEnoughBrokers, ClientNotFound, MaxPriceLessThanMinimumPrice, BrokerNotFound {
        auctionHouse.getAuctions().put(1, new Auction(4, 1));
        auctionHouse.checkAuction(1, 1, 3100);
        Exception exception = assertThrows(ClientAlreadyEnrolledForAuction.class, () -> auctionHouse.checkAuction(1, 1, 3000));
        assertEquals(exception.getMessage(), "Client with id 1 already enrolled at auction 1.");
        exception = assertThrows(MaxPriceLessThanMinimumPrice.class, () -> auctionHouse.checkAuction(2, 1, 100));
        assertEquals(exception.getMessage(), "Client with id 2 paid too less for the product 1.");
    }

    @Test
    @DisplayName("Test one Client will win the auction")
    @Order(8)
    public void testClientWinsTheAuction()
            throws ClientAlreadyEnrolledForAuction, ProductNotFound, NotEnoughBrokers, ClientNotFound, MaxPriceLessThanMinimumPrice, BrokerNotFound {
        auctionHouse.getAuctions().put(2, new Auction(4, 2));
        auctionHouse.checkAuction(1, 2, 2000);
        auctionHouse.checkAuction(2, 2, 800);
        auctionHouse.checkAuction(4, 2, 700);
        assertFalse(auctionHouse.getAuctions().containsKey(2));
        assertTrue(auctionHouse.getClients().get(1).getNumberAuctionWins() == 1
                || auctionHouse.getClients().get(2).getNumberAuctionWins() == 1
                || auctionHouse.getClients().get(4).getNumberAuctionWins() == 1);
        assertTrue(auctionHouse.getBrokers().stream().anyMatch(broker -> broker.getAccumulatedSum() > 0));
        auctionHouse.getClients().get(1).setNumberAuctionWins(0);
        auctionHouse.getClients().get(2).setNumberAuctionWins(0);
        auctionHouse.getClients().get(4).setNumberAuctionWins(0);
    }

    @Test
    @DisplayName("Test Client can participate to more than one auction")
    @Order(9)
    public void testClientCanParticipateToMoreThanOneAuction()
            throws ClientAlreadyEnrolledForAuction, ProductNotFound, NotEnoughBrokers, ClientNotFound, MaxPriceLessThanMinimumPrice, BrokerNotFound {
        auctionHouse.getAuctions().put(3, new Auction(4, 3));
        auctionHouse.getAuctions().put(4, new Auction(4, 4));
        auctionHouse.checkAuction(1, 4, 9999);
        auctionHouse.checkAuction(1, 3, 9999);
        int counterAuctions = 0;
        for (Broker broker : auctionHouse.getBrokers()) {
            if (broker.getClients().get(1) != null && broker.getClients().get(1).stream()
                    .anyMatch(clientDoublePair -> clientDoublePair.getKey().getId() == 1)) {
                counterAuctions++;
            }
            if (broker.getClients().get(4) != null && broker.getClients().get(4).stream()
                    .anyMatch(clientDoublePair -> clientDoublePair.getKey().getId() == 1)) {
                counterAuctions++;
            }
            if (broker.getClients().get(3) != null && broker.getClients().get(3).stream()
                    .anyMatch(clientDoublePair -> clientDoublePair.getKey().getId() == 1)) {
                counterAuctions++;
            }
        }
        assertEquals(3, counterAuctions);
    }

    @Test
    @DisplayName("Test Client wins earlier")
    @Order(10)
    public void testClientWinsEarlier()
            throws ClientAlreadyEnrolledForAuction, ProductNotFound, NotEnoughBrokers, ClientNotFound, MaxPriceLessThanMinimumPrice, BrokerNotFound {
        auctionHouse.getAuctions().put(5, new Auction(4, 5));
        auctionHouse.checkAuction(1, 5, 1300);
        auctionHouse.checkAuction(2, 5, 301);
        auctionHouse.checkAuction(3, 5, 302);
        assertEquals(auctionHouse.getClients().get(1).getNumberAuctionWins(), 1);
    }
}
