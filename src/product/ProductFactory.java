package product;

/**
 * Product factory.
 */
public class ProductFactory {
    /**
     * create the product based on the given type.
     * @param productType the product type.
     * @param name the name of the product.
     * @param minPrice the minimum price of the product.
     * @param year fabrication year of the product.
     * @param characteristicElem1 first characteristic element of the product.
     * @param characteristicElem2 second characteristic element of the product.
     * @return the created product from the given parameters based on the product type.
     */
    public Product getProduct(ProductType productType, String name, double minPrice, int year,
                              String characteristicElem1, String characteristicElem2) {
        if (productType == ProductType.FURNITURE) {
            return new FurnitureBuilder()
                    .withName(name)
                    .withMinPrice(minPrice)
                    .withYear(year)
                    .withMaterial(characteristicElem1)
                    .withType(characteristicElem2)
                    .build();
        } else if (productType == ProductType.JEWEL) {
            return new JewelBuilder()
                    .withName(name)
                    .withMinPrice(minPrice)
                    .withYear(year)
                    .withGem(Boolean.parseBoolean(characteristicElem1))
                    .withMaterial(characteristicElem2)
                    .build();
        } else if (productType == ProductType.PAINTING) {
            return new PaintingBuilder()
                    .withName(name)
                    .withMinPrice(minPrice)
                    .withYear(year)
                    .withPainterName(characteristicElem1)
                    .withColors(Colors.valueOf(characteristicElem2))
                    .build();
        } else {
            throw new IllegalArgumentException("Product type should be FURNITURE, JEWEL or PAINTING.");
        }
    }
}
