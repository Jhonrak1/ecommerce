package com.suarez.presentation.controller;

import com.suarez.domain.model.Book;
import com.suarez.domain.model.Clothing;
import com.suarez.domain.model.Electronics;
import com.suarez.domain.model.Product;
import com.suarez.domain.model.enums.ProductType;
import com.suarez.domain.service.ProductService;
import com.suarez.presentation.controller.dto.ProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Products", description = "Product Management API")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @Operation(summary = "Create a new product")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        Product product;

        switch (productDto.getCategory()) {
            case ELECTRONICS:
                product = Electronics.builder()
                        .name(productDto.getName())
                        .description(productDto.getDescription())
                        .price(productDto.getPrice())
                        .stock(productDto.getStock())
                        .category(productDto.getCategory())
                        .brand(productDto.getBrand())
                        .model(productDto.getModel())
                        .warrantyMonths(productDto.getWarrantyMonths())
                        .build();
                break;
            case CLOTHING:
                product = Clothing.builder()
                        .name(productDto.getName())
                        .description(productDto.getDescription())
                        .price(productDto.getPrice())
                        .stock(productDto.getStock())
                        .category(productDto.getCategory())
                        .size(productDto.getSize())
                        .color(productDto.getColor())
                        .material(productDto.getMaterial())
                        .build();
                break;
            case BOOKS:
                product = Book.builder()
                        .name(productDto.getName())
                        .description(productDto.getDescription())
                        .price(productDto.getPrice())
                        .stock(productDto.getStock())
                        .category(productDto.getCategory())
                        .author(productDto.getAuthor())
                        .isbn(productDto.getIsbn())
                        .pages(productDto.getPages())
                        .build();
                break;
            default:
                product = Product.builder()
                        .name(productDto.getName())
                        .description(productDto.getDescription())
                        .price(productDto.getPrice())
                        .stock(productDto.getStock())
                        .category(productDto.getCategory())
                        .build();
        }

        Product savedProduct = productService.createProduct(product);
        ProductDto responseDto = mapToDto(savedProduct);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing product")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) {
        Product product = Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .stock(productDto.getStock())
                .category(productDto.getCategory())
                .build();

        Product updatedProduct = productService.updateProduct(id, product);
        ProductDto responseDto = mapToDto(updatedProduct);

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        Product product = productService.findById(id);
        ProductDto responseDto = mapToDto(product);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    @Operation(summary = "Get all products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<Product> products = productService.findAll();
        List<ProductDto> responseDtos = products.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/category/{category}")
    @Operation(summary = "Get products by category")
    public ResponseEntity<List<ProductDto>> getProductsByCategory(@PathVariable ProductType category) {
        List<Product> products = productService.findByCategory(category);
        List<ProductDto> responseDtos = products.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/price-range")
    @Operation(summary = "Get products by price range")
    public ResponseEntity<List<ProductDto>> getProductsByPriceRange(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max) {
        List<Product> products = productService.findByPriceRange(min, max);
        List<ProductDto> responseDtos = products.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseDtos);
    }

    @GetMapping("/search")
    @Operation(summary = "Search products by name")
    public ResponseEntity<List<ProductDto>> searchProductsByName(@RequestParam String name) {
        List<Product> products = productService.searchByName(name);
        List<ProductDto> responseDtos = products.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseDtos);
    }

    private ProductDto mapToDto(Product product) {
        ProductDto dto = ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .category(product.getCategory())
                .build();

        if (product instanceof Electronics electronics) {
            dto.setBrand(electronics.getBrand());
            dto.setModel(electronics.getModel());
            dto.setWarrantyMonths(electronics.getWarrantyMonths());
        } else if (product instanceof Clothing clothing) {
            dto.setSize(clothing.getSize());
            dto.setColor(clothing.getColor());
            dto.setMaterial(clothing.getMaterial());
        } else if (product instanceof Book book) {
            dto.setAuthor(book.getAuthor());
            dto.setIsbn(book.getIsbn());
            dto.setPages(book.getPages());
        }

        return dto;
    }
}
