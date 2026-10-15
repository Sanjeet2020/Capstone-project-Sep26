package com.sanj.projectcatalogueservice.dto;

import com.sanj.projectcatalogueservice.model.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private String name;
    private Double price; //BigDecimal in prod
    private String description;
    private String imageUrl;
    private Category category;
}
