package product;

public class FurnitureBuilder implements ProductBuilder<Furniture, FurnitureBuilder>{
    Furniture furniture = new Furniture();

    @Override
    public FurnitureBuilder withId(int id) {
        furniture.setId(id);
        return this;
    }

    @Override
    public FurnitureBuilder withName(String name) {
        furniture.setName(name);
        return this;
    }

    @Override
    public FurnitureBuilder withMinPrice(double minPrice) {
        furniture.setMinPrice(minPrice);
        return this;
    }

    @Override
    public Furniture build() {
        return furniture;
    }

    public FurnitureBuilder withType(String type) {
         furniture.setType(type);
         return this;
    }

    public FurnitureBuilder withMaterial(String material) {
        furniture.setMaterial(material);
        return this;
    }
}
