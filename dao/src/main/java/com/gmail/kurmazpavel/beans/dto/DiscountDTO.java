package com.gmail.kurmazpavel.beans.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DiscountDTO {
    private Long id;
    private String name;
    private Integer percent;
    private LocalDateTime last;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public LocalDateTime getLast() {
        return last;
    }

    public void setLast(LocalDateTime last) {
        this.last = last;
    }

}
