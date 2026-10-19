package com.sanj.projectcatalogueservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(indexes = @Index(name = "idx_product_name",columnList = "name"))
public class Product extends BaseEntity {
    private String name;
    private Double price; //BigDecimal in prod
    private String description;
    private String imageUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;
}
