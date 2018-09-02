package com.gmail.kurmazpavel.beans;

import org.hibernate.annotations.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="audit")
public class Audit implements Serializable{
    @GenericGenerator(name = "generator", strategy = "foreign", parameters = @org.hibernate.annotations.Parameter(name = "property", value = "user"))
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "ID", updatable = false, nullable = false)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private User user;
    @Column (name = "EVENT_TYPE")
    private String event_type;
    @Column(name = "CREATED")
    private LocalDateTime created;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getEvent_type() {
        return event_type;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

}

