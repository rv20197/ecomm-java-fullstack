package com.vatsalrajgor.eCommerce.service.Category;

import com.vatsalrajgor.eCommerce.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements CategoryServiceInterface{
    private final List<Category> categories = new ArrayList<>();
    private Long nextId = 1L;

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public Category createCategory(Category category) {
        category.setCategoryId(nextId++);
        categories.add(category);
        return category;
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categories.stream()
                .filter(c->c.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Category with id: %s not found", categoryId)));
        categories.remove(category);
        return "Category deleted successfully!";
    }

    @Override
    public Category updateCategory(Category category,  Long categoryId) {
        Category existingCategory = categories.stream()
                .filter(c->c.getCategoryId().equals(categoryId))
                .findFirst()
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Category with id: %s not found", categoryId)));
        existingCategory.setCategoryName(category.getCategoryName());
        return existingCategory;

    }
}
