package com.gmail.kurmazpavel.dto;

import java.time.LocalDateTime;

public class AuditDTO {
    private Long id;
    private String event;
    private LocalDateTime created;
    private UserDTO user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated (LocalDateTime created) {
        this.created = created;
    }

    public String toString() {
        return "id: " + id + ", Event: " + event + ", Date&Time: " + created;
    }
}
