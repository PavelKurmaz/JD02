package com.gmail.kurmazpavel.beans.dto;

import java.time.LocalDateTime;

public class AuditDTO {
    private long ID;
    private long user_id;
    private String event_type;
    private LocalDateTime localDateTime;

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public long getID() {  return ID;  }

    public String getEvent_type() {
        return event_type;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String toString() {
        return "ID: " + ID + ", Event: " + event_type + ", Date&Time: " + localDateTime;
    }
}
