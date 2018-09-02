package com.gmail.kurmazpavel.beans;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;
    @Column(name = "COMPLETED", nullable = false)
    private long completed;
    @Column(name = "USERS_ID", nullable = false)
    private long users_ID;

    public Order(long id, long completed, long users_ID) {
        this.id = id;
        this.completed = completed;
        this.users_ID = users_ID;
    }

    public Order() {}

    public long getId() {
        return id;
    }

    public long getCompleted() {
        return completed;
    }

    public long getUsers_ID() {
        return users_ID;
    }

    public void setId(Long id) {
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
