package com.ecommerce.order.adapter.out;

import com.ecommerce.order.domain.OrderDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "order_generator")
	@Column(name = "order_id")
	private String id;

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	@OneToMany(mappedBy = "order")
	List<OrderItemEntity> orderItemEntities;

	private UUID userId;

	private String addr;
	private String name;
	private String phoneNum;

	public static OrderEntity from(OrderDto.Request request, OrderGenerator orderGenerator) {
		return OrderEntity.builder()
				.userId(request.getUserId())
				.addr(request.getAddr())
				.phoneNum(request.getPhoneNum())
				.name(request.getName())
				.orderStatus(OrderStatus.NOT_ORDERED)
				.build();
	}

	public void addOrderItemEntities(OrderItemEntity orderItemEntity) {
		if (this.orderItemEntities == null) {
			this.orderItemEntities = new ArrayList<>();
		}
		orderItemEntity.setOrder(this);
		this.orderItemEntities.add(orderItemEntity);
	}

//	public String getOrderName(){
//		String customOrderName = "";
//
//		if (this.orderItemEntities != null && this.orderItemEntities.size() > 0) {
//			customOrderName = this.orderItemEntities.size() > 1 ?
//					this.orderItemEntities.get(0).getProductName() + " 외 " + (this.orderItemEntities.size() - 1) + "개" :
//					this.orderItemEntities.get(0).getProductName();
//		}
//		return customOrderName;
//	}

	public Long getTotalAmount() {
		return this.orderItemEntities.stream()
				.mapToLong(orderItemEntity -> orderItemEntity.getTotalAmount())
				.sum();
	}

	public Long getTotalQuantity() {
		return this.orderItemEntities.stream()
				.mapToLong(orderItemEntity -> orderItemEntity.getTotalQuantity())
				.sum();
	}

	public void changeStatus(OrderStatus orderStatus){
		this.orderStatus = orderStatus;
	}
}
