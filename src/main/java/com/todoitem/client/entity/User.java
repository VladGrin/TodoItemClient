package com.todoitem.client.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

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
}


