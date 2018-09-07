package com.gmail.kurmazpavel.beans.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class CommentDTO {
    private Long id;
    private String content;
    private LocalDateTime created;
    private long user_id;
    private long news_id;

    public CommentDTO(Long id, String content, LocalDateTime created, long user_id, long news_id) {
        this.id = id;
        this.content = content;
        this.created = created;
        this.user_id = user_id;
        this.news_id = news_id;
    }

    public CommentDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getNews_id() {
        return news_id;
    }

    public void setNews_id(long news_id) {
        this.news_id = news_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentDTO)) return false;
        CommentDTO that = (CommentDTO) o;
        return user_id == that.user_id &&
                Objects.equals(id, that.id) &&
                Objects.equals(content, that.content) &&
                Objects.equals(news_id, that.news_id) &&
                Objects.equals(created, that.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, created, user_id, news_id);
    }
}
