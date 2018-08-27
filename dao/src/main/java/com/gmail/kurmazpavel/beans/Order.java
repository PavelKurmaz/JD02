package com.gmail.kurmazpavel.beans;

public class Order {
    private long id;
    private int completed;
    private int users_ID;

    public Order(long id, int completed, int users_ID) {
        this.id = id;
        this.completed = completed;
        this.users_ID = users_ID;
    }

    public Order() {}

    public long getId() {
        return id;
    }

    public int getCompleted() {
        return completed;
    }

    public int getUsers_ID() {
        return users_ID;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public void setUsers_ID(int users_ID) {
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
