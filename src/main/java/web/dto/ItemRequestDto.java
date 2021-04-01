package web.dto;

import domain.item.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemRequestDto {

    private String name;
    private int price;
    private int stockQuantity;
}
