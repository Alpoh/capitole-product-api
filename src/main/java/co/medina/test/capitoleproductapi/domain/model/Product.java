package co.medina.test.capitoleproductapi.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Table(name = "product")
@Entity
@Data
@RequiredArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final String sku;  // Identificador único

    private final double price;
    private final String description;
    private final String category;

    @CreationTimestamp
    @Column(updatable = false) // La fecha de creación no debe actualizarse
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // Constructor sin argumentos necesario para JPA
    public Product() {
        this.sku = null;
        this.price = 0.0;
        this.description = null;
        this.category = null;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
