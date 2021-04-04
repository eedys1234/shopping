package com.java.shop.domain.item;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@DiscriminatorValue("B")
@Entity
public class Book extends Item {

    private String author;
    private String isbn;
}
