package com.java.shop;

import com.java.shop.domain.Address;
import com.java.shop.domain.Member;
import com.java.shop.domain.Order;
import com.java.shop.domain.OrderStatus;
import com.java.shop.domain.item.Book;
import com.java.shop.domain.item.Item;
import com.java.shop.dto.OrderRequestDto;
import com.java.shop.repository.ItemRepository;
import com.java.shop.repository.MemberRepository;
import com.java.shop.repository.OrderRepository;
import com.java.shop.service.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderService orderService;

    @Test
    public void 상품주문() throws Exception {

        //given
        Member member = createMember();
        Item item = createItem();

        String city = "서울";
        String zip_code = "123-123";
        String street = "독산로";

        int order_price = item.getPrice();
        int order_count = 2;

        OrderRequestDto requestDto = OrderRequestDto.builder()
                .order_count(order_count)
                .order_price(order_price)
                .city(city)
                .item_id(item.getId())
                .member_id(member.getId())
                .zip_code(zip_code)
                .street(street)
                .build();

        //when
        Order order = orderService.order(requestDto);

        //then
        Assert.assertEquals("주문 상태가 ORDER", OrderStatus.ORDER, order.getOrderStatus());
        assertEquals("주문의 상품 종류가 같아야 합니다.", 1, order.getOrderItems().size());
        assertEquals("주문 가격은 수량 * 가격이다.", 10000*2, order.getTotalPrice());
        assertEquals("주문의 남아있는 수량은 같아야 합니다.", 8, item.getStockQuantity());
    }

    @Test
    public void 상품주문_재고수량_초과() throws Exception {
        Member member = createMember();
        Item item = createItem();

        String city = "서울";
        String zip_code = "123-123";
        String street = "독산로";

        int order_price = item.getPrice();
        int order_count = 11;

        OrderRequestDto requestDto = OrderRequestDto.builder()
                .order_count(order_count)
                .order_price(order_price)
                .city(city)
                .item_id(item.getId())
                .member_id(member.getId())
                .zip_code(zip_code)
                .street(street)
                .build();

        //when
        Order order = orderService.order(requestDto);
        //then
        fail("상품 재고 수량 에러 발생");
    }

    @Test
    public void 상품주문_취소() throws Exception {
        //given
        Member member = createMember();
        Item item = createItem();

        String city = "서울";
        String zip_code = "123-123";
        String street = "독산로";

        int order_price = item.getPrice();
        int order_count = 2;

        OrderRequestDto requestDto = OrderRequestDto.builder()
                .order_count(order_count)
                .order_price(order_price)
                .city(city)
                .item_id(item.getId())
                .member_id(member.getId())
                .zip_code(zip_code)
                .street(street)
                .build();

        Order order = orderService.order(requestDto);

        //when
        orderService.cancel(order.getId());

        //then
        assertEquals("상품주문 상태가 CANCEL 처리되었습니다.", OrderStatus.CANCLE, order.getOrderStatus());
        assertEquals("주문 취소된 상품의 재고는 증가해야합니다.", 10, item.getStockQuantity());

    }

    private Member createMember() {

        Address address = Address.builder()
                                .city("서울")
                                .street("독산로")
                                .zipcode("123-123")
                                .build();

        Member member = Member.builder()
                .name("User A")
                .address(address)
                .build();

        memberRepository.save(member);
        return member;
    }

    private Item createItem() {

        Book book = new Book();
        book.setName("JPA 완전정복");
        book.setPrice(10000);
        book.setStockQuantity(10);

        itemRepository.save(book);

        return book;
    }
}
