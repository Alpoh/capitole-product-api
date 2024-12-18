package co.medina.test.capitoleproductapi.application.service.impl;

import co.medina.test.capitoleproductapi.application.service.CategoryService;
import co.medina.test.capitoleproductapi.domain.model.Category;
import co.medina.test.capitoleproductapi.domain.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Optional<Category> validateCategoryByName(String categoryName) {
        return categoryRepository.findByName(categoryName);
    }
}
