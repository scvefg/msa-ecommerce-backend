package com.ecommerce.product.application.port.in;

import com.ecommerce.product.domain.model.ProductDto;

import java.util.List;

public interface ProductUseCase {

	ProductDto.Response createProduct(ProductDto.Request request);
	ProductDto.Response modifyProduct(ProductDto.Request request, Long productId);
	ProductDto.Response deleteProduct(Long productId);
	String getProductsName(List<Long> productIds);

	List<ProductDto.Simple> getProducts();
}
