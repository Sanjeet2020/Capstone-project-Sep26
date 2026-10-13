package com.sanj.projectcatalogueservice.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Category extends BaseEntity {
    private String name;
    private String description;
    private List<Product> products;
}
