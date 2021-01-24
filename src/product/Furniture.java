package product;

public class Furniture extends Product {
    private String type;
    private String material;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "Furniture{" +
                "id=" + super.getId() +
                ", name=" + super.getName() +
                ", minPrice=" + super.getMinPrice() +
                ", sellPrice=" + super.getSellPrice() +
                ", type=" + type  +
                ", material=" + material +
                '}';
    }
}
