package product;

import java.util.Objects;

public abstract class Product {
    private int id;
    private String name;
    private double sellPrice;
    private double minPrice;
    private int year;
    protected static int counterProducts = 1;

    protected Product(String name, double minPrice, int year) {
        this.id = counterProducts++;
        this.name = name;
        this.sellPrice = 0;
        this.minPrice = minPrice;
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Double.compare(product.sellPrice, sellPrice) == 0 && Double.compare(product.minPrice, minPrice) == 0 && year == product.year && name.equals(product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sellPrice, minPrice, year);
    }

    @Override
    public String toString() {
        return "{name='" + name + '\'' +
                ", sellPrice=" + sellPrice +
                ", minPrice=" + minPrice +
                ", year=" + year +
                '}';
    }
}
