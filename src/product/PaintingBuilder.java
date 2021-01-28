package product;

public class PaintingBuilder implements ProductBuilder<Painting, PaintingBuilder> {
    Painting painting = new Painting();

    @Override
    public PaintingBuilder withId(int id) {
        painting.setId(id);
        return this;
    }

    @Override
    public PaintingBuilder withName(String name) {
        painting.setName(name);
        return this;
    }

    @Override
    public PaintingBuilder withMinPrice(double minPrice) {
        painting.setMinPrice(minPrice);
        return this;
    }

    @Override
    public PaintingBuilder withYear(int year) {
        painting.setYear(year);
        return this;
    }

    @Override
    public Painting build() {
        painting.setId(Product.counterProducts);
        Product.updateCounter();
        return painting;
    }

    public PaintingBuilder withPainterName(String painterName) {
        painting.setPainterName(painterName);
        return this;
    }

    public PaintingBuilder withColors(Colors colors) {
        painting.setColors(colors);
        return this;
    }
}
