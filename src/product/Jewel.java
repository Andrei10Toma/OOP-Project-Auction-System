package product;

public class Jewel extends Product {
    private String material;
    private boolean gem;

    public Jewel(String name, double minPrice, int year, String material, boolean gem) {
        super(name, minPrice, year);
        this.material = material;
        this.gem = gem;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public boolean isGem() {
        return gem;
    }

    public void setGem(boolean gem) {
        this.gem = gem;
    }

    @Override
    public String toString() {
        return "Jewel" + super.toString() +
                ", material=" + material  +
                ", gem=" + gem +
                "}\n";
    }
}
