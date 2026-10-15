package com.sanj.projectcatalogueservice.repository;

import com.sanj.projectcatalogueservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
