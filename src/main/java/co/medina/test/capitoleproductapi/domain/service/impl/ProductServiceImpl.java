package co.medina.test.capitoleproductapi.domain.service.impl;

import co.medina.test.capitoleproductapi.domain.model.Product;
import co.medina.test.capitoleproductapi.domain.repository.ProductRepository;
import co.medina.test.capitoleproductapi.domain.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductBySku(String sku) {
        return productRepository.findById(sku);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(String sku, Product product) {
        return productRepository.findById(sku).map(existingProduct -> {
            Product updatedProduct = new Product(
                    sku, // Reutiliza el SKU original
                    product.getPrice(),
                    product.getDescription(),
                    product.getCategory()
            );
            return productRepository.save(updatedProduct);
        }).orElseThrow(() -> new RuntimeException("Product not found with SKU: " + sku));
    }


    @Override
    public void deleteProduct(String sku) {
        productRepository.deleteById(sku);
    }

}
