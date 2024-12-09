package co.medina.test.capitoleproductapi.unit.domain.service.impl;

import co.medina.test.capitoleproductapi.application.service.CategoryService;
import co.medina.test.capitoleproductapi.application.service.impl.ProductServiceImpl;
import co.medina.test.capitoleproductapi.domain.model.Category;
import co.medina.test.capitoleproductapi.domain.model.Product;
import co.medina.test.capitoleproductapi.domain.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryService categoryService;

    private Product product;

    private AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
        productService = new ProductServiceImpl(productRepository, categoryService);
        product = new Product("SKU001", 100.0, "Test Product");
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void loadContext() {
        Assertions.assertNotNull(productService);
        Assertions.assertNotNull(productRepository);
    }

    @Test
    void testCreateProduct() {
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(product);

        Product createdProduct = productService.createProduct(product);

        Assertions.assertNotNull(createdProduct);
        Assertions.assertEquals(product.getSku(), createdProduct.getSku());
        Mockito.verify(productRepository, Mockito.times(1)).save(product);
    }

    @Test
    void testRetrieveProductBySku_Success() {
        Mockito.when(productRepository.findById(Mockito.anyString())).thenReturn(Optional.of(product));

        Optional<Product> retrievedProduct = productService.retrieveProductBySku("SKU0001");

        Assertions.assertTrue(retrievedProduct.isPresent());
        Assertions.assertEquals(product.getSku(), retrievedProduct.get().getSku());
        Mockito.verify(productRepository, Mockito.times(1)).findById("SKU0001");
    }

    @Test
    void testRetrieveProductBySku_NotFound() {
        Mockito.when(productRepository.findById("SKU002")).thenReturn(Optional.empty());

        Optional<Product> retrievedProduct = productService.retrieveProductBySku("SKU002");

        Assertions.assertFalse(retrievedProduct.isPresent());
        Mockito.verify(productRepository, Mockito.times(1)).findById("SKU002");
    }

    @Test
    void testRetrieveAllProducts() {
        Mockito.when(productRepository.findAll()).thenReturn(List.of(product));

        List<Product> products = productService.retrieveAllProducts();

        Assertions.assertEquals(1, products.size());
        Assertions.assertEquals(product.getSku(), products.get(0).getSku());
        Mockito.verify(productRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testUpdateProduct_Success() {
        Product updatedProduct = new Product("SKU001", 200.0, "Updated Product");

        Mockito.when(productRepository.findById(Mockito.anyString())).thenReturn(Optional.of(updatedProduct));
        Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(updatedProduct);

        Product result = productService.updateProduct("SKU001", updatedProduct);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(updatedProduct.getPrice(), result.getPrice());
        Mockito.verify(productRepository, Mockito.times(1)).findById("SKU001");
        Mockito.verify(productRepository, Mockito.times(1)).save(Mockito.any(Product.class));
    }

    @Test
    void testUpdateProduct_NotFound() {
        Mockito.when(productRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());

        Product updatedProduct = new Product("SKU001", 200.0, "Updated Product");

        Assertions.assertThrows(RuntimeException.class, () -> productService.updateProduct("SKU001", updatedProduct));
        Mockito.verify(productRepository, Mockito.times(1)).findById("SKU001");
    }

    @Test
    void testDeleteProduct_Success() {
        Mockito.doNothing().when(productRepository).deleteById("SKU001");

        Assertions.assertDoesNotThrow(() -> productService.deleteProduct("SKU001"));
        Mockito.verify(productRepository, Mockito.times(1)).deleteById("SKU001");
    }

    @Test
    void testDeleteProduct_NotFound() {
        Mockito.doThrow(new RuntimeException("Product not found")).when(productRepository).deleteById("SKU002");

        Assertions.assertThrows(RuntimeException.class, () -> productService.deleteProduct("SKU002"));
        Mockito.verify(productRepository, Mockito.times(1)).deleteById("SKU002");
    }

    @Test
    void validateCategoryByName_ShouldReturnCategory_WhenCategoryExists() {
        // Arrange
        String categoryName = "Electronics";
        Category mockCategory = new Category("Electronics", "Electronic devices");
        Mockito.when(categoryService.validateCategoryByName(categoryName)).thenReturn(Optional.of(mockCategory));

        // Act
        Optional<Category> result = productService.validateCategoryByName(categoryName);

        // Assert
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("Electronics", result.get().getName());
        Mockito.verify(categoryService, Mockito.times(1)).validateCategoryByName(categoryName);
    }

    @Test
    void validateCategoryByName_ShouldReturnEmpty_WhenCategoryDoesNotExist() {
        // Arrange
        String categoryName = "NonExistingCategory";
        Mockito.when(categoryService.validateCategoryByName(categoryName)).thenReturn(Optional.empty());

        // Act
        Optional<Category> result = productService.validateCategoryByName(categoryName);

        // Assert
        Assertions.assertFalse(result.isPresent());
        Mockito.verify(categoryService, Mockito.times(1)).validateCategoryByName(categoryName);
    }
}