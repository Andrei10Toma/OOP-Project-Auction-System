package product;

public abstract class AbstractProductBuilder<E extends Product, T extends ProductBuilder<E, T>> implements ProductBuilder<E, T> {
    protected int id;
    protected String name;
    protected double minPrice;
    protected int year;

    @Override
    public T withId(int id) {
        this.id = id;
        return self();
    }

    @Override
    public T withName(String name) {
        this.name = name;
        return self();
    }

    @Override
    public T withMinPrice(double minPrice) {
        this.minPrice = minPrice;
        return self();
    }

    @Override
    public T withYear(int year) {
        this.year = year;
        return self();
    }

    @Override
    public E build() {
        return internalBuild();
    }

    protected abstract E internalBuild();

    @SuppressWarnings("unchecked")
    public T self() {
        return (T) this;
    }
}
