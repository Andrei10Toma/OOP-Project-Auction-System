package product;

/**
 * Builder for the furniture products.
 */
public class FurnitureBuilder extends AbstractProductBuilder<Furniture, FurnitureBuilder>{
    private String type;
    private String material;

    /**
     * set the type of the furniture.
     * @param type type of the furniture.
     * @return this FurnitureBuilder object.
     */
    public FurnitureBuilder withType(String type) {
        this.type = type;
        return this;
    }

    /**
     * set the material of the furniture.
     * @param material the material of the furniture.
     * @return this FurnitureBuilder object.
     */
    public FurnitureBuilder withMaterial(String material) {
        this.material = material;
        return this;
    }

    /**
     * build the Furniture object.
     * @return built Furniture object.
     */
    @Override
    protected Furniture internalBuild() {
        return new Furniture(this.name, this.minPrice, this.year, this.type, this.material);
    }
}
