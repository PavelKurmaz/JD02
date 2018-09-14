package com.gmail.kurmazpavel.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "discount")
public class Discount implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "ID")
    private Long id;
    @Column (name = "name")
    private String name;
    @Column (name = "percent")
    private Integer percent;
    @Column (name = "last")
    private LocalDateTime last;

    @OneToMany(mappedBy = "discount", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

    @ManyToMany (cascade = CascadeType.ALL)
    @JoinTable(
            name = "item_discount",
            joinColumns = @JoinColumn(name = "Discount_id"),
            inverseJoinColumns = @JoinColumn(name = "Item_id")
    )
    private List<Catalog> discountItems = new ArrayList<>();

    public List<Catalog> getDiscountItems() {
        return discountItems;
    }

    public void setDiscountItems(List<Catalog> discountItems) {
        this.discountItems = discountItems;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public LocalDateTime getLast() {
        return last;
    }

    public void setLast(LocalDateTime last) {
        this.last = last;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return Objects.equals(id, discount.id) &&
                Objects.equals(name, discount.name) &&
                Objects.equals(percent, discount.percent) &&
                Objects.equals(last, discount.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, percent, last);
    }
}
