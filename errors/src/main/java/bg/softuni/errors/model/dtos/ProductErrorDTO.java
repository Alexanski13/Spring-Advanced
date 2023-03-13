package bg.softuni.errors.model.dtos;

public class ProductErrorDTO {
    private final long productId;
    private final String description;

    public ProductErrorDTO(long productId, String description) {
        this.productId = productId;
        this.description = description;
    }

    public long getProductId() {
        return productId;
    }

    public String getDescription() {
        return description;
    }
}
