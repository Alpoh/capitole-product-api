package co.medina.test.capitoleproductapi.application.service.impl;

import co.medina.test.capitoleproductapi.application.service.ProductService;
import co.medina.test.capitoleproductapi.domain.model.Product;
import co.medina.test.capitoleproductapi.domain.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Optional<Product> retrieveProductBySku(String sku) {
        return productRepository.findById(sku);
    }

    @Override
    public List<Product> retrieveAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(String sku, Product product) {
        return productRepository.findById(sku).map(existingProduct -> {
            Product updatedProduct = new Product(
                    sku,
                    product.getPrice(),
                    product.getDescription()
            );
            return productRepository.save(updatedProduct);
        }).orElseThrow(() -> new RuntimeException("Product not found with SKU: " + sku));
    }


    @Override
    public void deleteProduct(String sku) {
        productRepository.deleteById(sku);
    }


    @Override
    public Page<Product> retrieveAllProductsPageable(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> retriveProductsSorted(String sortBy, String order) {
        Sort.Direction direction = "desc".equalsIgnoreCase(order) ? Sort.Direction.DESC : Sort.Direction.ASC;
        return productRepository.findAll(Sort.by(direction, sortBy));
    }

}
