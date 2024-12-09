package co.medina.test.capitoleproductapi.application.service;

import co.medina.test.capitoleproductapi.domain.model.Category;

import java.util.Optional;

public interface CategoryService {
    Optional<Category> validateCategoryByName(String categoryName);
}
