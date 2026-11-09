package com.sanj.projectcatalogueservice.service;

import com.sanj.projectcatalogueservice.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;
    @Override
    public Double getAvgPriceForCategory(String category) {
        return categoryRepository.findAvgPriceForCategory(category);
    }
}
