package com.sanj.projectcatalogueservice.service;

import com.sanj.projectcatalogueservice.dto.ProductDto;
import com.sanj.projectcatalogueservice.model.Product;

public interface IProductService {

    Product getProductById(long id);

    Product createProduct(ProductDto request);

    boolean deleteProduct(long id);

    Product updateProduct(long id, ProductDto request);
}
