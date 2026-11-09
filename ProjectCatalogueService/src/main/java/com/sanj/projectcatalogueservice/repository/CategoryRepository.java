package com.sanj.projectcatalogueservice.repository;

import com.sanj.projectcatalogueservice.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);

    @Query("SELECT AVG(p.price) FROM Product p WHERE p.category.name = :category")
    Double findAvgPriceForCategory(String category);
}
