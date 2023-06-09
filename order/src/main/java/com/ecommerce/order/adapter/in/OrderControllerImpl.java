package com.ecommerce.order.adapter.in;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ecommerce.order.application.port.in.OrderUseCase;
import com.ecommerce.order.domain.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {

	private final OrderUseCase orderUseCase;

	@Override
	public ResponseEntity<?> createOrder(OrderDto.Request request) throws JsonProcessingException {
		return ResponseEntity.ok(orderUseCase.createOrder(request));
	}

	@Override
	public ResponseEntity<?> cancelOrder(String orderId) {
		orderUseCase.cancelOrder(orderId);
		return ResponseEntity.ok().build();
	}

	@Override
	public ResponseEntity<?> orderList(OrderDto.Request request) {
		return ResponseEntity.ok(orderUseCase.getOrders(request));
	}

//	@Override
//	public ResponseEntity<?> getPayUrl(String orderId) {
//		return ResponseEntity.ok(orderUseCase.getPayUrl(orderId));
//	}

}
