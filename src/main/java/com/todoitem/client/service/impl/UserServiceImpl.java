package com.todoitem.client.service.impl;

import com.todoitem.client.entity.User;
import com.todoitem.client.repository.UserRepository;
import com.todoitem.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
