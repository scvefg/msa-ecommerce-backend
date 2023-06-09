package com.ecommerce.product.adapter.in;

import com.ecommerce.product.application.port.in.ProductUseCase;
import com.ecommerce.product.domain.model.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController{

	private final ProductUseCase productUseCase;
//	private final KafkaTemplate<String, ProductDto.Request> kafkaTemplate;
//	private final String TOPIC = "product-topic";

//	public void sendMessage(ProductDto.Request request){
//		kafkaTemplate.send(TOPIC, request);
//	}

	@Override
	public ResponseEntity<?> getProducts() {
		return ResponseEntity.ok(productUseCase.getProducts());

	}

	@Override
	public ResponseEntity<?> createProduct(@RequestBody ProductDto.Request request) {
		return ResponseEntity.ok(productUseCase.createProduct(request));
	}

	@Override
	public ResponseEntity<?> modifyProduct(@RequestBody ProductDto.Request request, Long productId) {
		return ResponseEntity.ok(productUseCase.modifyProduct(request, productId));
	}

	@Override
	public ResponseEntity<?> deleteProduct(@RequestBody Long productId) {
		return ResponseEntity.ok(productUseCase.deleteProduct(productId));
	}

	@Override
	public ResponseEntity<?> getProductsName(@RequestBody List<Long> productIds) {
		return ResponseEntity.ok(productUseCase.getProductsName(productIds));
	}
}
