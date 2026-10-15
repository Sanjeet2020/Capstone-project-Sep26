package com.sanj.projectcatalogueservice.service;

import com.sanj.projectcatalogueservice.dto.ProductDto;
import com.sanj.projectcatalogueservice.model.Category;
import com.sanj.projectcatalogueservice.model.Product;
import com.sanj.projectcatalogueservice.model.enums.State;
import com.sanj.projectcatalogueservice.repository.CategoryRepository;
import com.sanj.projectcatalogueservice.repository.ProductRepository;

import java.util.Date;

public class StorageProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public StorageProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product createProduct(ProductDto request) {
        // Convert ProductDto to Product entity
        Product product = fromDto(request);
        //check category exists
        product.setCategory(findOrCreateCategory(request.getCategory().getId()));
        product.setCreatedAt(new Date());
        product.setLasUpdatedAt(new Date());
        // Save the product to the database
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public boolean deleteProduct(long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setState(State.INACTIVE);
            product.setLasUpdatedAt(new Date());
            productRepository.save(product);
            return true;
        }
        return false;
    }

    private Category findOrCreateCategory(long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        if(category == null || category.getName() == null) {
            return null;
        }
        return categoryRepository.findByName(category.getName()).orElseGet(() -> {
            Category newCategory = new Category();
            return categoryRepository.save(newCategory);
        });
    }

    private Product fromDto(ProductDto request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setImageUrl(request.getImageUrl());
        //product.setCategory(request.getCategory());
        return product;
    }
}
