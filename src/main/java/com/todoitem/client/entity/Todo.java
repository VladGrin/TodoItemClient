package com.todoitem.client.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
//@Builder
@Table(name = "todo")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "todo_id")
    private Long todoId;

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

    public Todo(Long todoId, String subject, String dueDate, boolean done, User user) {
        this.todoId = todoId;
        this.subject = subject;
        this.dueDate = dueDate;
        this.done = done;
        this.user = user;
    }
}
