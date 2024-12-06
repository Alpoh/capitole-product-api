package co.medina.test.capitoleproductapi.infrastructure.api;

import co.medina.test.capitoleproductapi.application.service.ProductService;
import co.medina.test.capitoleproductapi.domain.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/products")
@Tag(name = "Products", description = "Endpoints for managing products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Create a new product")
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.createProduct(product));
    }

    @Operation(summary = "Get a product by SKU")
    @GetMapping("/{sku}")
    public ResponseEntity<Product> getProduct(@PathVariable String sku) {
        return productService.retrieveProductBySku(sku)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all products")
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.retrieveAllProducts();
    }

    @Operation(summary = "Update a product by SKU")
    @PutMapping("/{sku}")
    public ResponseEntity<Product> updateProduct(
            @PathVariable String sku,
            @RequestBody Product product) {
        return ResponseEntity.ok(productService.updateProduct(sku, product));
    }

    @Operation(summary = "Delete a product by SKU")
    @DeleteMapping("/{sku}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String sku) {
        productService.deleteProduct(sku);
        return ResponseEntity.noContent().build();
    }
}
