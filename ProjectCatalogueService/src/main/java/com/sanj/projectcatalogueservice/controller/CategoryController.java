package com.sanj.projectcatalogueservice.controller;

import com.sanj.projectcatalogueservice.model.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return List.of();
    }

    @GetMapping("/categories/{id}")
    public Category getCategoryById(@PathVariable int id) {
        return new Category();
    }

    public void deleteCategory(int id) {
        // Logic to delete a category by its ID
    }
}
