package co.medina.test.capitoleproductapi.integration.impl;

import co.medina.test.capitoleproductapi.application.service.impl.ProductServiceImpl;
import co.medina.test.capitoleproductapi.domain.model.Product;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@Transactional
@DataJpaTest
@Import(ProductServiceImpl.class)
@Disabled
class ProductServiceImplTest {

    private final ProductServiceImpl productService;

    @Autowired
    public ProductServiceImplTest(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @Test
    void loadContext() {
        Assertions.assertNotNull(productService);
    }

    @Test
    void testCreateProduct() {
        Product product = new Product("SKU001", 100.0, "Test Product");

        Product createdProduct = productService.createProduct(product);

        Assertions.assertNotNull(createdProduct);
        Assertions.assertEquals("SKU001", createdProduct.getSku());
        Assertions.assertEquals(100.0, createdProduct.getPrice());
        Assertions.assertEquals("Test Product", createdProduct.getDescription());
    }

    @Test
    void testRetrieveProductBySku_Success() {
        Product product = new Product("SKU002", 200.0, "Another Product");
        productService.createProduct(product);

        Optional<Product> retrievedProduct = productService.retrieveProductBySku("SKU002");

        Assertions.assertTrue(retrievedProduct.isPresent());
        Assertions.assertEquals("SKU002", retrievedProduct.get().getSku());
    }

    @Test
    void testRetrieveProductBySku_NotFound() {
        Optional<Product> retrievedProduct = productService.retrieveProductBySku("NON_EXISTENT");

        Assertions.assertFalse(retrievedProduct.isPresent());
    }

    @Test
    void testRetrieveAllProducts() {
        productService.createProduct(new Product("SKU003", 150.0, "Product 1"));
        productService.createProduct(new Product("SKU004", 250.0, "Product 2"));

        List<Product> allProducts = productService.retrieveAllProducts();

        Assertions.assertEquals(2, allProducts.size());
    }

    @Test
    void testUpdateProduct_Success() {
        Product product = new Product("SKU005", 300.0, "Old Product");
        productService.createProduct(product);

        Product updatedProduct = new Product("SKU005", 500.0, "Updated Product");

        Product result = productService.updateProduct("SKU005", updatedProduct);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(500.0, result.getPrice());
        Assertions.assertEquals("Updated Product", result.getDescription());
    }

    @Test
    void testUpdateProduct_NotFound() {
        Product updatedProduct = new Product("SKU006", 500.0, "Updated Product");

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () ->
                productService.updateProduct("SKU006", updatedProduct));

        Assertions.assertEquals("Product not found with SKU: SKU006", exception.getMessage());
    }

    @Test
    void testDeleteProduct_Success() {
        Product product = new Product("SKU007", 150.0, "Product to Delete");
        productService.createProduct(product);

        productService.deleteProduct("SKU007");

        Optional<Product> deletedProduct = productService.retrieveProductBySku("SKU007");
        Assertions.assertFalse(deletedProduct.isPresent());
    }
}