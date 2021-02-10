package product;

import java.util.Objects;

/**
 * Representation of a Painting product.
 */
public class Painting extends Product{
    private String painterName;
    private Colors colors;

    /**
     * constructor for Painting object.
     * @param name name of the product.
     * @param minPrice minimum price of the product.
     * @param year fabrication year of the product.
     * @param painterName painter name of the painting.
     * @param colors colors used for the painting.
     */
    public Painting(String name, double minPrice, int year, String painterName, Colors colors) {
        super(name, minPrice, year);
        this.painterName = painterName;
        this.colors = colors;
    }

    public String getPainterName() {
        return painterName;
    }

    public void setPainterName(String painterName) {
        this.painterName = painterName;
    }

    public Colors getColors() {
        return colors;
    }

    public void setColors(Colors colors) {
        this.colors = colors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Painting)) return false;
        if (!super.equals(o)) return false;
        Painting painting = (Painting) o;
        return painterName.equals(painting.painterName) && colors == painting.colors;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), painterName, colors);
    }

    @Override
    public String toString() {
        return "Painting" + super.toString() +
                ", painter name=" + painterName  +
                ", colors=" + colors +
                "}\n";
    }
}
