package com.example.consumer.service;

import com.example.consumer.model.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ConsumerService {

    Mono<Product> getProductById(Long id);

    Flux<Product> getAllProducts();
}
