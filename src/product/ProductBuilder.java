package product;

public interface ProductBuilder<E extends Product, T extends ProductBuilder<E, T>> {
    T withId(int id);
    T withName(String name);
    T withMinPrice(double minPrice);
    E build();
}
