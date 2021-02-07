package product;

public class JewelBuilder extends AbstractProductBuilder<Jewel, JewelBuilder> {
    private String material;
    private boolean gem;

    public JewelBuilder withMaterial(String material) {
        this.material = material;
        return this;
    }

    public JewelBuilder withGem(boolean gem) {
        this.gem = gem;
        return this;
    }

    @Override
    protected Jewel internalBuild() {
        return new Jewel(this.name, this.minPrice, this.year, this.material, this.gem);
    }
}
