package co.medina.test.capitoleproductapi.domain.repository;

import co.medina.test.capitoleproductapi.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
}
