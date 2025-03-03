package com.suarez.domain.service;

import com.suarez.domain.model.Product;
import com.suarez.domain.model.enums.ProductType;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product updateProduct(Long id, Product product);
    void deleteProduct(Long id);
    Product findById(Long id);
    List<Product> findAll();
    List<Product> findByCategory(ProductType category);
    List<Product> findByPriceRange(BigDecimal min, BigDecimal max);
    List<Product> searchByName(String name);
}
