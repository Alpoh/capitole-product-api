package co.medina.test.capitoleproductapi.application.service;

import co.medina.test.capitoleproductapi.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createProduct(Product product);

    Optional<Product> retrieveProductBySku(String sku);

    List<Product> retrieveAllProducts();

    Product updateProduct(String sku, Product product);

    void deleteProduct(String sku);
}
