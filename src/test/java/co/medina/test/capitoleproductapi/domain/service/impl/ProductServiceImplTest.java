package co.medina.test.capitoleproductapi.domain.service.impl;

import co.medina.test.capitoleproductapi.domain.model.Product;
import co.medina.test.capitoleproductapi.domain.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        product = new Product("SKU666", 100.0, "Test Product", "Electronics");
    }

    @Test
    void loadContext() {
        Assertions.assertNotNull(productService);
        Assertions.assertNotNull(productRepository);
    }

    @Test
    void testCreateProduct() {
        Mockito.when(productRepository.save(product)).thenReturn(product);

        Product createdProduct = productService.createProduct(product);

        Assertions.assertNotNull(createdProduct);
        Assertions.assertEquals(product.getSku(), createdProduct.getSku());
        Mockito.verify(productRepository, Mockito.times(1)).save(product);
    }

    @Test
    void testGetProductBySku_Success() {
        Mockito.when(productRepository.findById("SKU001")).thenReturn(Optional.of(product));

        Optional<Product> retrievedProduct = productService.getProductBySku("SKU001");

        Assertions.assertTrue(retrievedProduct.isPresent());
        Assertions.assertEquals(product.getSku(), retrievedProduct.get().getSku());
        Mockito.verify(productRepository, Mockito.times(1)).findById("SKU001");
    }

    @Test
    void testGetProductBySku_NotFound() {
        Mockito.when(productRepository.findById("SKU002")).thenReturn(Optional.empty());

        Optional<Product> retrievedProduct = productService.getProductBySku("SKU002");

        Assertions.assertFalse(retrievedProduct.isPresent());
        Mockito.verify(productRepository, Mockito.times(1)).findById("SKU002");
    }

    @Test
    void testGetAllProducts() {
        Mockito.when(productRepository.findAll()).thenReturn(List.of(product));

        List<Product> products = productService.getAllProducts();

        Assertions.assertEquals(1, products.size());
        Assertions.assertEquals(product.getSku(), products.get(0).getSku());
        Mockito.verify(productRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testUpdateProduct_Success() {
        Mockito.when(productRepository.findById("SKU001")).thenReturn(Optional.of(product));
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        Product updatedProduct = new Product("SKU001", 200.0, "Updated Product", "Electronics");
        Product result = productService.updateProduct("SKU001", updatedProduct);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(updatedProduct.getPrice(), result.getPrice());
        Mockito.verify(productRepository, Mockito.times(1)).findById("SKU001");
        Mockito.verify(productRepository, Mockito.times(1)).save(Mockito.any(Product.class));
    }

    /*@Test
    void testUpdateProduct_NotFound() {
        Mockito.when(productRepository.findById("SKU001")).thenReturn(Optional.empty());

        Product updatedProduct = new Product("SKU001", 200.0, "Updated Product", "Electronics");

        Assertions.assertThrows(ProductNotFoundException.class, () -> {
            productService.updateProduct("SKU001", updatedProduct);
        });
        Mockito.verify(productRepository, Mockito.times(1)).findById("SKU001");
    }*/

    @Test
    void testDeleteProduct_Success() {
        Mockito.doNothing().when(productRepository).deleteById("SKU001");

        Assertions.assertDoesNotThrow(() -> productService.deleteProduct("SKU001"));
        Mockito.verify(productRepository, Mockito.times(1)).deleteById("SKU001");
    }

    @Test
    void testDeleteProduct_NotFound() {
        Mockito.doThrow(new RuntimeException("Product not found")).when(productRepository).deleteById("SKU002");

        Assertions.assertThrows(RuntimeException.class, () -> {
            productService.deleteProduct("SKU002");
        });
        Mockito.verify(productRepository, Mockito.times(1)).deleteById("SKU002");
    }
}