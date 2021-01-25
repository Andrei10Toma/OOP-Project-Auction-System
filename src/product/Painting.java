package product;

public class Painting extends Product{
    private String painterName;
    private Colors colors;

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
    public String toString() {
        return "Painting{" +
                "id=" + super.getId() +
                ", name=" + super.getName() +
                ", minPrice=" + super.getMinPrice() +
                ", sellPrice=" + super.getSellPrice() +
                ", painter name=" + painterName  +
                ", colors=" + colors +
                '}';
    }
}
