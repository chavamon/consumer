package com.example.consumer.service;

import com.example.consumer.model.Product;
import com.example.consumer.persistence.ProductRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    Logger logger = Logger.getLogger(ConsumerServiceImpl.class.getName());
    private final ProductRepository productRepository;

    public ConsumerServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Mono<Product> getProductById(Long id) {
        logger.info("Fetching product with id {}" + id);
        return productRepository.findById(id);
    }

    public Flux<Product> getAllProducts() {
        logger.info("Fetching all products");
        return productRepository.findAll();
    }

}
