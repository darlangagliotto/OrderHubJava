package com.darlan.orderservice.infra.client;

import com.darlan.orderservice.application.port.ProductCatalogPort;
import com.darlan.orderservice.exception.BusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class ProductCatalogRestClient implements ProductCatalogPort {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public ProductCatalogRestClient(
            RestTemplate restTemplate,
            @Value("${clients.product-service.base-url}") String baseUrl
    ) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    @Override
    public ProductSnapshot getProduct(UUID productId) {
        try {
            ProductResponse response = restTemplate.getForObject(
                    baseUrl + "/{id}",
                    ProductResponse.class,
                    productId
            );

            if (response == null) {
                throw new BusinessException("Produto não encontrado: " + productId);
            }

            return new ProductSnapshot(
                    response.id(),
                    response.price(),
                    response.stockQuantity()
            );
        } catch (RestClientException ex) {
            throw new BusinessException("Erro ao consultar produto: " + productId);
        }
    }

    @Override
    public void decreaseStock(UUID productId, int quantity) {
        try {
            restTemplate.postForLocation(
                    baseUrl + "/{id}/decrease-stock",
                    new DecreaseStockRequest(quantity),
                    productId
            );
        } catch (RestClientException ex) {
            throw new BusinessException("Erro ao baixar estoque do produto: " + productId);
        }
    }

    private record ProductResponse(UUID id, BigDecimal price, Integer stockQuantity) {}
    private record DecreaseStockRequest(Integer quantity) {}
}