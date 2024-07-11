package com.example.consumer.controller;

import com.example.consumer.model.Product;
import com.example.consumer.service.ConsumerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@RestController
@RequestMapping("/product/v1")
public class ProductController {

    private final Logger logger = Logger.getLogger(ProductController.class.getName());
    private final ConsumerService consumerService;

    public ProductController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<Mono<Product>> getProductById(@PathVariable Long id) {
        logger.info("Fetching product with id {}" + id);

        Mono<Product> productMono = consumerService.getProductById(id);
        return ResponseEntity.ok().body(productMono);
    }

    @GetMapping("/get/all")

    public ResponseEntity<Flux<Product>> getAllProducts() {
        logger.info("Fetching all products");

        Flux<Product> products = consumerService.getAllProducts();
        return ResponseEntity.ok().body(products);
    }


}
