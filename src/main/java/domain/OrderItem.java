package domain;

import domain.item.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "order_item")
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int orderPrice;

    private int count;

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setPrice(int price) {
        this.orderPrice = price;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void cancel() {
        this.item.addQuantity(count);
    }

    public int getTotalPrice() {
        return this.getOrderPrice() * this.getCount();
    }

    public static OrderItem createOrderItem(Item item, int price, int count) {
        OrderItem orderItem = new OrderItem();
        item.removeQuantity(count);
        orderItem.setItem(item);
        orderItem.setPrice(price);
        orderItem.setCount(count);
        return orderItem;
    }
}
