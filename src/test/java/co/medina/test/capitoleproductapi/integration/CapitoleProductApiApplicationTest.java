package co.medina.test.capitoleproductapi.integration;

import co.medina.test.capitoleproductapi.CapitoleProductApiApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CapitoleProductApiApplicationTest {

    private final CapitoleProductApiApplication capitoleProductApiApplication;

    @Autowired
    public CapitoleProductApiApplicationTest(CapitoleProductApiApplication capitoleProductApiApplication) {
        this.capitoleProductApiApplication = capitoleProductApiApplication;
    }

    @Test
    void contextLoads() {
        Assertions.assertNotNull(capitoleProductApiApplication);
    }

}
