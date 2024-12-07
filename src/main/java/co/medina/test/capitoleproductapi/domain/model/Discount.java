package co.medina.test.capitoleproductapi.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "DISCOUNT")
@Data
@RequiredArgsConstructor
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id;
    private final String name;
    private final String description;
    private final BigDecimal percentage;

    // Constructor
    public Discount() {
        this.id = null;
        this.name = null;
        this.description = null;
        this.percentage = null;
    }
}
