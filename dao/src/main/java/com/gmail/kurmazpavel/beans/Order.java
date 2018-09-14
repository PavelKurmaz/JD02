package com.gmail.kurmazpavel.beans;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @EmbeddedId
    private OrderId orderId;
    @Column(name = "CREATED", nullable = false)
    private LocalDateTime created;
    @Column(name = "QUANTITY", nullable = false)
    private int quantity;
    @Column(name = "BUCKET_ID")
    private Long bucketId;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("User_Id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("Item_Id")
    private Catalog item;

    public Order (User user, Catalog item) {
        this.user = user;
        this.item = item;
        this.orderId = new OrderId(user.getId(), item.getId());
    }

    public Order (User user, Catalog item, int quantity, LocalDateTime localDateTime) {
        this.user = user;
        this.quantity = quantity;
        this.created = localDateTime;
        this.item = item;
        this.orderId = new OrderId(user.getId(), item.getId());
    }

    public Order () {}

    public Long getBucketId() {
        return bucketId;
    }

    public void setBucketId(Long bucketId) {
        this.bucketId = bucketId;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public void setOrderId(OrderId orderId) {
        this.orderId = orderId;
    }

    public Catalog getItem() {
        return item;
    }

    public void setItem(Catalog item) {
        this.item = item;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return quantity == order.quantity &&
                Objects.equals(orderId, order.orderId) &&
                Objects.equals(created, order.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, created, quantity);
    }
}
