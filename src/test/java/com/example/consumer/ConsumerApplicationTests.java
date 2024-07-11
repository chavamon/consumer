package com.example.consumer;

import com.example.consumer.model.Product;
import com.example.consumer.persistence.ProductRepository;
import com.example.consumer.service.ConsumerService;
import com.example.consumer.service.ConsumerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class ConsumerApplicationTests {

		@Mock
		private ProductRepository productRepository;

		private ConsumerService consumerService;

		@BeforeEach
		public void setUp() {
			MockitoAnnotations.openMocks(this);
			consumerService = new ConsumerServiceImpl(productRepository);
		}

		@Test
		public void getProductById_WhenProductExists() {
			Long productId = 1L;
			Mono<Product> mockProduct =  Mono.just(new Product(productId, "Test Product", "Description", 10.0));
			when(productRepository.findById(productId)).thenReturn(mockProduct);

			Mono<Product> result = consumerService.getProductById(productId);

			assertEquals(mockProduct, result);
		}

		@Test
		public void getProductById_WhenProductDoesNotExist() {
			Long productId = 1L;
			when(productRepository.findById(productId)).thenReturn(Mono.empty());

			Mono<Product> result = consumerService.getProductById(productId);

			assertEquals(result,Mono.empty());
		}
	}