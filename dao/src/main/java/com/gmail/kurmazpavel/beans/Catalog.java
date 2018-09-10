package com.gmail.kurmazpavel.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "catalog")
public class Catalog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    @Column(name = "AMOUNTLEFT", nullable = false)
    private long amount;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "PRICE")
    private double price;
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> users = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {

        return id;
    }

    public List<Order> getUsers() {
        return users;
    }

    public Catalog(long id, int amount, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public Catalog() {
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

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Catalog)) return false;
        Catalog catalog = (Catalog) o;
        return amount == catalog.amount &&
                Double.compare(catalog.price, price) == 0 &&
                Objects.equals(id, catalog.id) &&
                Objects.equals(name, catalog.name) &&
                Objects.equals(users, catalog.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, name, price, users);
    }
}
