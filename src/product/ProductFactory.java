package product;

public class ProductFactory {
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
