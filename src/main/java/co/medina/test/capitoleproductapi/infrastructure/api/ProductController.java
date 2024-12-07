package co.medina.test.capitoleproductapi.infrastructure.api;

import co.medina.test.capitoleproductapi.application.service.ProductService;
import co.medina.test.capitoleproductapi.domain.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Product> retrieveProduct(@PathVariable String sku) {
        return productService.retrieveProductBySku(sku)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all products")
    @GetMapping
    public List<Product> retrieveAllProducts() {
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

    @Operation(summary = "Get all products pageable")
    @GetMapping("/pageable")
    public ResponseEntity<Page<Product>> retrieveAllProductsPageable(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Product> products = productService.retrieveAllProductsPageable(PageRequest.of(page, size));
        return ResponseEntity.ok(products);
    }

    @Operation(summary = "Get all products sorted by SKU, Price, Description and Category")
    @GetMapping("/sort")
    public ResponseEntity<List<Product>> getProductsSorted(
            @RequestParam String sortBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        if (!List.of("sku", "price", "description", "category").contains(sortBy.toLowerCase())) {
            throw new IllegalArgumentException("Invalid sort field: " + sortBy);
        }
        List<Product> products = productService.retriveProductsSorted(sortBy, order);
        return ResponseEntity.ok(products);
    }
}
