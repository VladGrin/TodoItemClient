package com.javateam.taskmanager.service;

import com.javateam.taskmanager.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> findAll();

    void save(User user);

}
