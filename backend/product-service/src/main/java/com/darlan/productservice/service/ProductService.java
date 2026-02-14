package com.darlan.productservice.service;

import com.darlan.productservice.dto.ProductRequestDto;
import com.darlan.productservice.dto.ProductResponseDto;
import com.darlan.productservice.entity.Product;
import com.darlan.productservice.exception.ProductNotFoundException;
import com.darlan.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<ProductResponseDto> getAllProducts() {
        return productRepository.findAll()
            .stream()
            .map(this::toDto)
            .toList();
    }

    @Transactional(readOnly = true)
    public ProductResponseDto getById(UUID id) {
        Product product = findById(id);
        return toDto(product);
    }

    @Transactional(readOnly = true)
    public ProductResponseDto create(ProductRequestDto request) {
        Product product = new Product(request.name(), request.price());
        Product saved = productRepository.save(product);
        return toDto(saved);
    }

    @Transactional(readOnly = true)
    public ProductResponseDto update(UUID id, ProductRequestDto request) {
        Product product = findById(id);
        product.setName(request.name());
        product.setPrice(request.price());
        Product saved = productRepository.save(product);
        return toDto(saved);
    }

    @Transactional(readOnly = true)    
    public void delete(UUID id) {
        Product product = findById(id);
        productRepository.delete(product);
    }

    @Transactional(readOnly = true)
    private Product findById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
            return product;
    }

    private ProductResponseDto toDto(Product product) {
        return new ProductResponseDto(
            product.getId(), 
            product.getName(), 
            product.getPrice()
        );
    }
}
