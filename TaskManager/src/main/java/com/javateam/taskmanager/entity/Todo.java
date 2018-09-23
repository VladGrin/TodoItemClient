package com.javateam.taskmanager.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "todo")
public class Todo {
    @Id
    private Integer id;

    @Column(name = "subject")
    private String subject;

    @Column(name = "due_date")
    private String dueDate;

    @Column(name = "done")
    private boolean done;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Todo() {
    }
}
