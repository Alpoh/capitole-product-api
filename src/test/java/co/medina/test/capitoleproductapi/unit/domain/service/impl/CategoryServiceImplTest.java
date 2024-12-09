package co.medina.test.capitoleproductapi.unit.domain.service.impl;

import co.medina.test.capitoleproductapi.application.service.impl.CategoryServiceImpl;
import co.medina.test.capitoleproductapi.domain.model.Category;
import co.medina.test.capitoleproductapi.domain.repository.CategoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    private AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void validateCategoryByName_ShouldReturnCategory_WhenCategoryExists() {
        // Arrange
        String categoryName = "Electronics";
        Category mockCategory = new Category("Electronics", "Electronic devices");
        Mockito.when(categoryRepository.findByName(categoryName)).thenReturn(Optional.of(mockCategory));

        // Act
        Optional<Category> result = categoryService.validateCategoryByName(categoryName);

        // Assert
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("Electronics", result.get().getName());
        Mockito.verify(categoryRepository, Mockito.times(1)).findByName(categoryName);
    }

    @Test
    void validateCategoryByName_ShouldReturnEmpty_WhenCategoryDoesNotExist() {
        // Arrange
        String categoryName = "NonExistingCategory";
        Mockito.when(categoryRepository.findByName(categoryName)).thenReturn(Optional.empty());

        // Act
        Optional<Category> result = categoryService.validateCategoryByName(categoryName);

        // Assert
        Assertions.assertFalse(result.isPresent());
        Mockito.verify(categoryRepository, Mockito.times(1)).findByName(categoryName);
    }
}