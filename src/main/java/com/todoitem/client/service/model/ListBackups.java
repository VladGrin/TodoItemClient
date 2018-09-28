package com.todoitem.client.service.model;

import com.todoitem.client.entity.Status;
import lombok.Data;

@Data
public class ListBackups {

    private Long id;
    private String date;
    private Status status;

    public ListBackups(Long id, String date, Status status) {
        this.id = id;
        this.date = date;
        this.status = status;
    }
}
