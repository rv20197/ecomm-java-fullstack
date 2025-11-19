package com.vatsalrajgor.eCommerce.service.Category;

import com.vatsalrajgor.eCommerce.model.Category;

import java.util.List;

public interface CategoryServiceInterface {
    List<Category> getAllCategories();
    Category createCategory(Category category);
    String deleteCategory(Long categoryId);
    Category updateCategory(Category category, Long categoryId);
}
