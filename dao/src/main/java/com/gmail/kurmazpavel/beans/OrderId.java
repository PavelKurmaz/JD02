package com.gmail.kurmazpavel.beans;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderId implements Serializable{
    @Column(name = "User_Id")
    private Long userId;
    @Column(name = "Item_Id")
    private Long itemId;

    public OrderId(long userId, long itemId){
        this.userId = userId;
        this.itemId = itemId;
    }

    public OrderId(){}

    public void setUserId(Long user_id) {
        this.userId = user_id;
    }

    public void setItemId(Long item_id) {
        this.itemId = item_id;
    }

    public long getUserId() {
        return userId;
    }

    public long getItemId() {
        return itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderId orderId = (OrderId) o;
        return Objects.equals(userId, orderId.userId) &&
                Objects.equals(itemId, orderId.itemId);
    }

    @Override
    public int hashCode(){
        return Objects.hash(itemId, userId);
    }
}
