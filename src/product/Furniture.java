package product;

import java.util.Objects;

/**
 * Representation of a Furniture product object.
 */
public class Furniture extends Product {
    private String type;
    private String material;

    /**
     * constructor for Furniture product.
     * @param name name of the product.
     * @param minPrice minimum price of the product.
     * @param year fabrication year of the product.
     * @param type type of the furniture.
     * @param material material of the furniture.
     */
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Furniture)) return false;
        if (!super.equals(o)) return false;
        Furniture furniture = (Furniture) o;
        return type.equals(furniture.type) && material.equals(furniture.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type, material);
    }

    @Override
    public String toString() {
        return "Furniture" + super.toString() +
                ", type=" + type  +
                ", material=" + material +
                "}\n";
    }
}
