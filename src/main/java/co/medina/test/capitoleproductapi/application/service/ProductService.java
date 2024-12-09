package co.medina.test.capitoleproductapi.application.service;

import co.medina.test.capitoleproductapi.domain.model.Category;
import co.medina.test.capitoleproductapi.domain.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createProduct(Product product);

    Optional<Product> retrieveProductBySku(String sku);

    List<Product> retrieveAllProducts();

    Product updateProduct(String sku, Product product);

    void deleteProduct(String sku);

    Page<Product> retrieveAllProductsPageable(Pageable pageable);

    List<Product> retriveProductsSorted(String sortBy, String order);

    Optional<Category> validateCategoryByName(String categoryName);

    List<Product> retrieveProductsByCategory(Category validCategory);
}
