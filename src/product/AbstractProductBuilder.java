package product;

/**
 * generic builder for the product objects.
 * @param <E> a class that extends Product.
 * @param <T> a class that extends ProductBuilder<E, T>.
 */
public abstract class AbstractProductBuilder<E extends Product, T extends ProductBuilder<E, T>> implements ProductBuilder<E, T> {
    protected int id;
    protected String name;
    protected double minPrice;
    protected int year;

    /**
     * set the id of the product.
     * @param id id of the product.
     * @return T type of the builder.
     */
    @Override
    public T withId(int id) {
        this.id = id;
        return self();
    }

    /**
     * set the name of the product.
     * @param name name of the product.
     * @return T type of the builder.
     */
    @Override
    public T withName(String name) {
        this.name = name;
        return self();
    }

    /**
     * set the minimum price of the product.
     * @param minPrice minimum price of the product.
     * @return T type of the builder.
     */
    @Override
    public T withMinPrice(double minPrice) {
        this.minPrice = minPrice;
        return self();
    }

    /**
     * set the fabrication year of the product.
     * @param year fabrication year of the product.
     * @return T type of the builder.
     */
    @Override
    public T withYear(int year) {
        this.year = year;
        return self();
    }

    /**
     * build the product.
     * @return the built product.
     */
    @Override
    public E build() {
        return internalBuild();
    }

    /**
     * build the product of specific type.
     * @return the build method.
     */
    protected abstract E internalBuild();

    /**
     * cast the builder to the actual type that is needed.
     * @return casted builder to the needed type.
     */
    @SuppressWarnings("unchecked")
    public T self() {
        return (T) this;
    }
}
