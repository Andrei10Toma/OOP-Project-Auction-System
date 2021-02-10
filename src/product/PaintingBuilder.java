package product;

/**
 * Builder for the painting products.
 */
public class PaintingBuilder extends AbstractProductBuilder<Painting, PaintingBuilder> {
    private String painterName;
    private Colors colors;

    /**
     * set the name of the painter.
     * @param painterName name of the painter.
     * @return this PaintingBuilder object.
     */
    public PaintingBuilder withPainterName(String painterName) {
        this.painterName = painterName;
        return this;
    }

    /**
     * set the colors of the paint.
     * @param colors colors of the paint.
     * @return this PaintingBuilder object.
     */
    public PaintingBuilder withColors(Colors colors) {
        this.colors = colors;
        return this;
    }

    /**
     * build the Painting object.
     * @return built Painting object.
     */
    @Override
    protected Painting internalBuild() {
        return new Painting(this.name, this.minPrice, this.year, this.painterName, this.colors);
    }
}
