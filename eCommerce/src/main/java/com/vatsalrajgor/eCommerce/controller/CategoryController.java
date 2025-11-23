package com.vatsalrajgor.eCommerce.controller;

import com.vatsalrajgor.eCommerce.model.Category;
import com.vatsalrajgor.eCommerce.service.Category.CategoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/public/categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        return new ResponseEntity<>(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @PostMapping("/public/categories")
    public ResponseEntity<?> createCategory(@Valid @RequestBody Category category){
        return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/categories/{categoryId}")
    public ResponseEntity<String> deleteCategory(@Valid @PathVariable Long categoryId){
        return new ResponseEntity<>(categoryService.deleteCategory(categoryId), HttpStatus.OK);
    }

    @PutMapping("/public/categories/{categoryId}")
    public ResponseEntity<?> updateCategory(@Valid @RequestBody Category category, @PathVariable Long categoryId){
        return new ResponseEntity<>(categoryService.updateCategory(category, categoryId), HttpStatus.OK);
    }
}
