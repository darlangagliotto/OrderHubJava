package com.darlan.productservice.service;

import com.darlan.productservice.dto.DecreaseStockRequestDto;
import com.darlan.productservice.dto.ProductRequestDto;
import com.darlan.productservice.dto.ProductResponseDto;
import com.darlan.productservice.entity.Product;
import com.darlan.productservice.exception.BusinessException;
import com.darlan.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

    @Transactional
    public ProductResponseDto create(ProductRequestDto request) {
        int stock = Optional
            .ofNullable(request.stockQuantty())
            .orElse(0);

        Product product = new Product(request.name(), request.price(), stock);
        Product saved = productRepository.save(product);
        return toDto(saved);
    }

    @Transactional
    public ProductResponseDto update(UUID id, ProductRequestDto request) {
        Product product = findById(id);
        product.changeName(request.name());
        product.changePrice(request.price());
        Product saved = productRepository.save(product);
        return toDto(saved);
    }

    @Transactional
    public void decreaseStock(UUID id, Integer quantity) {
        if (quantity == null) {
            throw new BusinessException("Quantidade é obrigatória");
        }
        Product product = findById(id);
        product.decreaseStock(quantity);
        productRepository.save(product);
    }

    @Transactional
    public void delete(UUID id) {
        Product product = findById(id);
        productRepository.delete(product);
    }

    private Product findById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Produto não encontrado para o id: %s".formatted(id)));
            return product;
    }

    private ProductResponseDto toDto(Product product) {
        return new ProductResponseDto(
            product.getId(), 
            product.getName(), 
            product.getPrice(),
            product.getStockQuantity()
        );
    }
}
