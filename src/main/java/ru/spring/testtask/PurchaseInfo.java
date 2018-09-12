package ru.spring.testtask;

import java.math.BigDecimal;

/*Класс, который передаётся в представление*/
public class PurchaseInfo {
    private String _name;
    private int _count;
    private BigDecimal _price;
    private BigDecimal _total;

    PurchaseInfo(String name, int count, BigDecimal price, BigDecimal total) {
        _name = name;
        _count = count;
        _price = price;
        _total = total;
    }

    public String getName() {
        return _name;
    }

    public int getCount() {
        return _count;
    }

    public BigDecimal getPrice() {
        return _price;
    }

    public BigDecimal getTotal() {
        return _total;
    }
}
