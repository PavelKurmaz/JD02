package com.gmail.kurmazpavel.beans.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class BucketDTO {
    private Long id;
    private String status;
    private Long userId;
    private LocalDateTime created;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BucketDTO bucketDTO = (BucketDTO) o;
        return Objects.equals(id, bucketDTO.id) &&
                Objects.equals(status, bucketDTO.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }
}
