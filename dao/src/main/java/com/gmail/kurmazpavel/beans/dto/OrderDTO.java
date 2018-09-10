package com.gmail.kurmazpavel.beans.dto;

import java.time.LocalDateTime;

public class OrderDTO {
    private Long userId;
    private Long itemId;
    private LocalDateTime created;
    private int quantity;

    public OrderDTO(long userId, long itemId, LocalDateTime created, int quantity) {
        this.userId = userId;
        this.itemId = itemId;
        this.created = created;
        this.quantity = quantity;
    }

    public OrderDTO() {}

    public Long getUserId() {
        return userId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
