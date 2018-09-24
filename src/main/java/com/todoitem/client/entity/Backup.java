package com.todoitem.client.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
//@Builder
@Table(name = "backup")
public class Backup{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "date")
    private String date;

    @Column(name = "status")
    private Status status;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "backup")
    private List<User> users;

    public Backup() {
    }

    public Backup(String date, Status status, List<User> users) {
        this.date = date;
        this.status = status;
        this.users = users;
    }

}
