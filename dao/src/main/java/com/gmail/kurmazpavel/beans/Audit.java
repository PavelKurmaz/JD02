package com.gmail.kurmazpavel.beans;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="audit")
public class Audit implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;
    @Column(name = "Users_ID", nullable = false)
    private Long users_ID;
    @Column (name = "EVENT_TYPE")
    private String event_type;
    @Column(name = "CREATED")
    private LocalDateTime created;

    public Long getId() {
        return id;
    }
    public Long setId(long id) {
        return id;
    }

    public Long getUsers_ID() {
        return users_ID;
    }

    public String getEvent_type() {
        return event_type;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setUsers_ID(Long users_ID) {
        this.users_ID = users_ID;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

}

