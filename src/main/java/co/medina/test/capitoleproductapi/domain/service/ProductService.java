package co.medina.test.capitoleproductapi.domain.service;

import co.medina.test.capitoleproductapi.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createProduct(Product product);
    Optional<Product> getProductBySku(String sku);
    List<Product> getAllProducts();
    Product updateProduct(String sku, Product product);
    void deleteProduct(String sku);
}
