package product;

/**
 * Builder for the jewel products.
 */
public class JewelBuilder extends AbstractProductBuilder<Jewel, JewelBuilder> {
    private String material;
    private boolean gem;

    /**
     * set the material of the jewel.
     * @param material material of the jewel.
     * @return this JewelBuilder object.
     */
    public JewelBuilder withMaterial(String material) {
        this.material = material;
        return this;
    }

    /**
     * set the gem of the jewel.
     * @param gem gem of the jewel.
     * @return this JewelBuilder object.
     */
    public JewelBuilder withGem(boolean gem) {
        this.gem = gem;
        return this;
    }

    /**
     * build the Jewel object.
     * @return built jewel product.
     */
    @Override
    protected Jewel internalBuild() {
        return new Jewel(this.name, this.minPrice, this.year, this.material, this.gem);
    }
}
