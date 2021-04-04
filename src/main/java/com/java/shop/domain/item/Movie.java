package com.java.shop.domain.item;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor
@Getter
@DiscriminatorValue("M")
@Entity
public class Movie extends Item {

    private String director;

    private String actor;
}
