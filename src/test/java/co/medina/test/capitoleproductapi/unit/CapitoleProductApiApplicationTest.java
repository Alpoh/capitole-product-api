package co.medina.test.capitoleproductapi.unit;

import co.medina.test.capitoleproductapi.CapitoleProductApiApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CapitoleProductApiApplicationTest {

    @InjectMocks
    CapitoleProductApiApplication capitoleProductApiApplication;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(capitoleProductApiApplication);
    }

}
