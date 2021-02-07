package product;

public class FurnitureBuilder extends AbstractProductBuilder<Furniture, FurnitureBuilder>{
    private String type;
    private String material;

    public FurnitureBuilder withType(String type) {
        this.type = type;
        return this;
    }

    public FurnitureBuilder withMaterial(String material) {
        this.material = material;
        return this;
    }

    @Override
    protected Furniture internalBuild() {
        return new Furniture(this.name, this.minPrice, this.year, this.type, this.material);
    }
}
