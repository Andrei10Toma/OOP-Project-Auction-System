package product;

import java.util.Objects;

/**
 * Representation of a Jewel product.
 */
public class Jewel extends Product {
    private String material;
    private boolean gem;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Jewel)) return false;
        if (!super.equals(o)) return false;
        Jewel jewel = (Jewel) o;
        return gem == jewel.gem && material.equals(jewel.material);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), material, gem);
    }

    /**
     * constructor for the Jewel product.
     * @param name name of the product.
     * @param minPrice minimum price of the product.
     * @param year fabrication year of the product.
     * @param material material of the Jewel.
     * @param gem jewel contains gem.
     */
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
