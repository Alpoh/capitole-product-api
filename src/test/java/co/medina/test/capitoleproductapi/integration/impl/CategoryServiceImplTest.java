package co.medina.test.capitoleproductapi.integration.impl;

import co.medina.test.capitoleproductapi.application.service.CategoryService;
import co.medina.test.capitoleproductapi.application.service.impl.CategoryServiceImpl;
import co.medina.test.capitoleproductapi.domain.model.Category;
import co.medina.test.capitoleproductapi.domain.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

@SpringBootTest
@Transactional
@DataJpaTest
@Import(CategoryServiceImpl.class)
@Disabled
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    void validateCategoryByName_ShouldReturnCategory_WhenCategoryExists() {
        // Arrange: Insertar datos en la base de datos H2
        Category savedCategory = categoryRepository.save(new Category("Electronics", "Electronic devices"));

        // Act
        Optional<Category> result = categoryService.validateCategoryByName("Electronics");

        // Assert
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("Electronics", result.get().getName());
        Assertions.assertEquals(savedCategory.getId(), result.get().getId());
    }

    @Test
    void validateCategoryByName_ShouldReturnEmpty_WhenCategoryDoesNotExist() {
        // Act
        Optional<Category> result = categoryService.validateCategoryByName("NonExistingCategory");

        // Assert
        Assertions.assertFalse(result.isPresent());
    }
}
