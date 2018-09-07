package com.gmail.kurmazpavel.beans.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class NewsDTO {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime created;
    private Long user_id;

    public NewsDTO(Long id, String title, String content, LocalDateTime created, Long user_id) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.created = created;
        this.user_id = user_id;
    }

    public NewsDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsDTO newsDTO = (NewsDTO) o;
        return Objects.equals(id, newsDTO.id) &&
                Objects.equals(title, newsDTO.title) &&
                Objects.equals(content, newsDTO.content) &&
                Objects.equals(created, newsDTO.created) &&
                Objects.equals(user_id, newsDTO.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, created, user_id);
    }
}
