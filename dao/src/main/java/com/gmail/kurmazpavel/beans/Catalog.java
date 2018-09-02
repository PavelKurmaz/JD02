package com.gmail.kurmazpavel.beans;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="catalog")
public class Catalog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long ID;
    @Column(name = "AMOUNTLEFT", nullable = false)
    private long amount;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "PRICE")
    private double price;


    public Catalog(long ID, int amount, String name, double price) {
        this.ID = ID;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public Catalog() {}

    public Long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getAmount() {
        return amount;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
