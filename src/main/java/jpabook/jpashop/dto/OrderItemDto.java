package jpabook.jpashop.dto;

import jpabook.jpashop.domain.OrderItem;
import lombok.Data;

@Data
public class OrderItemDto {

    private final String itemName;//상품 명
    private final int orderPrice; //주문 가격
    private final int count;      //주문 수량

    public OrderItemDto(OrderItem orderItem) {
        itemName = orderItem.getItem().getName();
        orderPrice = orderItem.getOrderPrice();
        count = orderItem.getCount();
    }
}
