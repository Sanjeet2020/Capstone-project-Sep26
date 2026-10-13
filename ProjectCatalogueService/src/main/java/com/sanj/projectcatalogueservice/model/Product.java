package com.sanj.projectcatalogueservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseEntity {
    private String name;
    private Double price; //BigDecimal in prod
    private String description;
    private String imageUrl;
    private Category category;
}
