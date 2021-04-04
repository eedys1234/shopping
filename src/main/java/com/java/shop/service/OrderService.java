package com.java.shop.service;

import com.java.shop.domain.Member;
import com.java.shop.domain.Order;
import com.java.shop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.java.shop.repository.ItemRepository;
import com.java.shop.repository.MemberRepository;
import com.java.shop.repository.OrderRepository;
import com.java.shop.dto.OrderRequestDto;

import java.util.Objects;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Order order(OrderRequestDto requestDto) {

        Member member = memberRepository.findOne(requestDto.getMember_id());

        if(Objects.isNull(member)) {
           throw new IllegalStateException("사용자가 존재하지 않습니다. id = " + requestDto.getMember_id());
        }

        Item item = itemRepository.findOne(requestDto.getItem_id());

        if(Objects.isNull(item)) {
            throw new IllegalStateException("상품이 존재하지 않습니다. id = " + requestDto.getItem_id());
        }

        Order order = requestDto.toEntity(member, item);
        orderRepository.save(order);

        return order;
    }

    public Long cancel(Long order_id) {

        Order order = orderRepository.findOne(order_id);

        order.cancel();
        return order_id;
    }
}
