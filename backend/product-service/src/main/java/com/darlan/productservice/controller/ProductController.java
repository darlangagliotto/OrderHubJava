package com.darlan.productservice.controller;

import com.darlan.productservice.dto.DecreaseStockRequestDto;
import com.darlan.productservice.dto.ProductRequestDto;
import com.darlan.productservice.dto.ProductResponseDto;
import com.darlan.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;


@RestController
@RequestMapping("api/product")
@Tag(name = "Produto", description = "Operações de CRUD de produtos")
public class ProductController {
    
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponseDto> getAll() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponseDto getById(@PathVariable UUID id) {
        return productService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponseDto create(@Valid @RequestBody ProductRequestDto request) {        
        return productService.create(request);
    }

    @PutMapping("{id}")    
    public ProductResponseDto update(@PathVariable UUID id, @Valid @RequestBody ProductRequestDto request) {
        return productService.update(id, request);
    }

    @PutMapping("/{id}/decrease-stock")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void decreaseStock(@PathVariable UUID id, @RequestBody DecreaseStockRequestDto request) {
        productService.decreaseStock(id, request.quantity());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        productService.delete(id);
    }    
}