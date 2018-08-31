package com.gmail.kurmazpavel.beans.dto;

import java.time.LocalDateTime;

public class AuditDTO {
    private long ID;
    private long Users_ID;
    private String event_type;
    private LocalDateTime localDateTime;

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setUsers_ID(long users_ID) {
        Users_ID = users_ID;
    }

    public void setEvent_type(String event_type) {
        this.event_type = event_type;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public long getID() {

        return ID;
    }

    public long getUsers_ID() {
        return Users_ID;
    }

    public String getEvent_type() {
        return event_type;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public String toString() {
        return "ID: " + ID + ", User_ID: " + Users_ID + ", Event: " + event_type + ", Date&Time: " + localDateTime;
    }
}
