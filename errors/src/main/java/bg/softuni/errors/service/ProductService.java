package bg.softuni.errors.service;

import bg.softuni.errors.model.exceptions.ProductNotFoundException;
import bg.softuni.errors.model.dtos.ProductDTO;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    public ProductDTO getProductByID(Long productId) {
        throw new ProductNotFoundException(productId);
    }
}
