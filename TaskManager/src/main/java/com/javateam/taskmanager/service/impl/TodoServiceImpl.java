package com.javateam.taskmanager.service.impl;

import com.javateam.taskmanager.repository.TodoRepository;
import com.javateam.taskmanager.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoRepository todoRepository;
}
