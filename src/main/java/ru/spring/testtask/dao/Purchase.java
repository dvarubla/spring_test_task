package ru.spring.testtask.dao;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "purchaseId")
    @SequenceGenerator(
            name = "purchaseId", sequenceName = "purchaseId",
            allocationSize = 1
    )
    private int id;

    @NaturalId
    private int num;

    private int count;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
