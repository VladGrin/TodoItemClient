package com.todoitem.client.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
//@Builder
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "backup_id", nullable = false)
    private Backup backup;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Todo> todos;

    public User() {
    }

    public User(Long userId, String username, String email, Backup backup, List<Todo> todos) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.backup = backup;
        this.todos = todos;
    }
}


