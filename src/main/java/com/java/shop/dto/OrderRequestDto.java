package com.java.shop.dto;

import com.java.shop.domain.*;
import com.java.shop.domain.item.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderRequestDto {

    private Long member_id;
    private Long item_id;
    private int order_count;
    private int order_price;

    private String zip_code;
    private String street;
    private String city;

    @Builder
    public OrderRequestDto(Long member_id, Long item_id, int order_count,
    int order_price, String zip_code, String street, String city) {

        this.member_id = member_id;
        this.item_id = item_id;
        this.order_count = order_count;
        this.order_price = order_price;
        this.zip_code = zip_code;
        this.street = street;
        this.city = city;

    }

    public Order toEntity(Member member, Item item) {

        Delivery delivery = new Delivery();
        Address address = Address.builder()
                .zipcode(zip_code)
                .street(street)
                .city(city)
                .build();
        delivery.setAddress(address);

        OrderItem orderItem = OrderItem.createOrderItem(item, order_price, order_count);

        return Order.createOrder(member, delivery, orderItem);
    }
}
