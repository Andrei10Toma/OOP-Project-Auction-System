package product;

public class Furniture extends Product {
    private String type;
    private String material;

    public Furniture(String name, double minPrice, int year, String type, String material) {
        super(name, minPrice, year);
        this.type = type;
        this.material = material;
    }

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
        return "Furniture" + super.toString() +
                ", type=" + type  +
                ", material=" + material +
                "}\n";
    }
}
