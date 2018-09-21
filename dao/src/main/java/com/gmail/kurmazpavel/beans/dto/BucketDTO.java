package com.gmail.kurmazpavel.beans.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BucketDTO {
    private Long id;
    private String status;
    private Long userId;
    private LocalDateTime created;
    private List<OrderDTO> orders= new ArrayList<>();

    public Long getUserId() {
        return userId;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDTO> orders) {
        this.orders = orders;
    }

}
