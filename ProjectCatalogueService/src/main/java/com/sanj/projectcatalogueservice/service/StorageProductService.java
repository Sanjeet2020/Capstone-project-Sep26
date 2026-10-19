package com.sanj.projectcatalogueservice.service;

import com.sanj.projectcatalogueservice.dto.ProductDto;
import com.sanj.projectcatalogueservice.model.Category;
import com.sanj.projectcatalogueservice.model.Product;
import com.sanj.projectcatalogueservice.model.enums.State;
import com.sanj.projectcatalogueservice.repository.CategoryRepository;
import com.sanj.projectcatalogueservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
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
        product.setCategory(findOrCreateCategory(request.getCategory()));
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

    @Override
    public Product updateProduct(long id, ProductDto request) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            // Update the product fields with the values from the request
            product.setName(request.getName());
            product.setPrice(request.getPrice());
            product.setDescription(request.getDescription());
            product.setImageUrl(request.getImageUrl());
            product.setLasUpdatedAt(new Date());
            return productRepository.save(product);
        }
        return null;
    }

    private Category findOrCreateCategory(Category category) {
        if (category == null) {
            return null;
        }

        Optional<Category> existing = categoryRepository.findByName(category.getName());
        if (existing.isPresent()) {
            return existing.get();
        }

        Date now = new Date();
        //category.setId(null);
        category.setCreatedAt(now);
        category.setLasUpdatedAt(now);
        return category;
    }

    private Product fromDto(ProductDto request) {
        Product product = new Product();
        //product.setId(request.getId());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setImageUrl(request.getImageUrl());

        if (request.getCategory() != null) {
            Category category = new Category();
            //category.setId(request.getCategory().getId());
            category.setName(request.getCategory().getName());
            category.setDescription(request.getCategory().getDescription());
            category.setState(State.ACTIVE);
            product.setCategory(category);
        }
        return product;
    }
}
