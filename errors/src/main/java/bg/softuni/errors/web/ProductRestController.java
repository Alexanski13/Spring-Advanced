package bg.softuni.errors.web;

import bg.softuni.errors.model.dtos.ProductDTO;
import bg.softuni.errors.model.dtos.ProductErrorDTO;
import bg.softuni.errors.model.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") long id) {
        throw new ProductNotFoundException(id);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductErrorDTO> onProductNotFound(ProductNotFoundException pnfe) {
        ProductErrorDTO productErrorDTO = new ProductErrorDTO(pnfe.getProductId(), "Product not found");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(productErrorDTO);
    }
}
