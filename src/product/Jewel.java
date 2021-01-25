package product;

public class Jewel extends Product {
    private String material;
    private boolean gem;

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
        return "Jewel{" +
                "id=" + super.getId() +
                ", name=" + super.getName() +
                ", minPrice=" + super.getMinPrice() +
                ", sellPrice=" + super.getSellPrice() +
                ", material=" + material  +
                ", gem=" + gem +
                "}\n";
    }
}
