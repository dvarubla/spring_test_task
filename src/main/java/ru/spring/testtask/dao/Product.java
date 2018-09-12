package ru.spring.testtask.dao;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.math.BigDecimal;
/*Класс для товаров*/
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productId")
    @SequenceGenerator(
            name = "productId", sequenceName = "productId",
            allocationSize = 1
    )
    private int id;

    @NaturalId
    private String name;

    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
