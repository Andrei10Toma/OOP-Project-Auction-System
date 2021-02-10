package product;

/**
 * Interface for the product builder.
 * @param <E> type that extends the product class.
 * @param <T> type that extends the product builder class.
 */
public interface ProductBuilder<E extends Product, T extends ProductBuilder<E, T>> {
    T withId(int id);
    T withName(String name);
    T withMinPrice(double minPrice);
    T withYear(int year);
    E build();
}
