package com.gmail.kurmazpavel.beans.dto;

public class OrderDTO {
    private Long id;
    private long completed;
    private long users_ID;

    public OrderDTO(Long id, long completed, long users_ID) {
        this.id = id;
        this.completed = completed;
        this.users_ID = users_ID;
    }

    public OrderDTO() {}

    public Long getId() {
        return id;
    }

    public long getCompleted() {
        return completed;
    }

    public long getUsers_ID() {
        return users_ID;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCompleted(long completed) {
        this.completed = completed;
    }

    public void setUsers_ID(long users_ID) {
        this.users_ID = users_ID;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", completed=" + completed +
                ", users_ID=" + users_ID +
                '}';
    }
}
