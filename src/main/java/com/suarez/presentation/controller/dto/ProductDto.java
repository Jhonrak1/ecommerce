package com.suarez.presentation.controller.dto;

import com.suarez.domain.model.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private ProductType category;

    // Electronics specific fields
    private String brand;
    private String model;
    private Integer warrantyMonths;

    // Clothing specific fields
    private String size;
    private String color;
    private String material;

    // Book specific fields
    private String author;
    private String isbn;
    private Integer pages;
}

