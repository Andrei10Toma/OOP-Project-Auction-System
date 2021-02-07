package product;

public class PaintingBuilder extends AbstractProductBuilder<Painting, PaintingBuilder> {
    private String painterName;
    private Colors colors;

    public PaintingBuilder withPainterName(String painterName) {
        this.painterName = painterName;
        return this;
    }

    public PaintingBuilder withColors(Colors colors) {
        this.colors = colors;
        return this;
    }

    @Override
    protected Painting internalBuild() {
        return new Painting(this.name, this.minPrice, this.year, this.painterName, this.colors);
    }
}
