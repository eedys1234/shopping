package web.dto;

import domain.item.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemResponseDto {

    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private String dtype;

    public ItemResponseDto(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.stockQuantity = item.getStockQuantity();
//        this.type = item.get
    }
}
