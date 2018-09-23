package com.todoitem.client.service;

import com.todoitem.client.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void save(User user);
}
