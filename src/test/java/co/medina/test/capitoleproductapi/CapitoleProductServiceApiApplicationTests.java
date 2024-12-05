package co.medina.test.capitoleproductapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CapitoleProductServiceApiApplicationTests {

    @InjectMocks
    CapitoleProductApiApplication capitoleProductApiApplication;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(capitoleProductApiApplication);
    }

}
