package product;

public class JewelBuilder implements ProductBuilder<Jewel, JewelBuilder> {
    Jewel jewel = new Jewel();

    @Override
    public JewelBuilder withId(int id) {
        jewel.setId(id);
        return this;
    }

    @Override
    public JewelBuilder withName(String name) {
        jewel.setName(name);
        return this;
    }

    @Override
    public JewelBuilder withMinPrice(double minPrice) {
        jewel.setMinPrice(minPrice);
        return this;
    }

    @Override
    public Jewel build() {
        return jewel;
    }

    public JewelBuilder withMaterial(String material) {
        jewel.setMaterial(material);
        return this;
    }

    public JewelBuilder withGem(boolean gem) {
        jewel.setGem(gem);
        return this;
    }
}
