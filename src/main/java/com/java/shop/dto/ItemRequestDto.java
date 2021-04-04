package com.java.shop.dto;

import com.java.shop.domain.item.Album;
import com.java.shop.domain.item.Book;
import com.java.shop.domain.item.Item;
import com.java.shop.domain.item.Movie;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ItemRequestDto {

    private String name;
    private int price;
    private int stockQuantity;
    private String dtype;

    public Item toEntity() {

        Item item = createItem(dtype);

        item.setName(name);
        item.setPrice(price);
        item.setStockQuantity(stockQuantity);

        return item;
    }

    public Item createItem(String dtype) {

        Item item = null;
        if("A".equals(dtype)) {
            item = new Album();
        }
        else if("B".equals(dtype)) {
            item = new Book();

        }
        else if("M".equals(dtype)) {
            item = new Movie();
        }

        return item;
    }


}
