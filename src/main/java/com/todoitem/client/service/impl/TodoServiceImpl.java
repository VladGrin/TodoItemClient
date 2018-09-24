package com.todoitem.client.service.impl;

import com.todoitem.client.entity.Todo;
import com.todoitem.client.repository.TodoRepository;
import com.todoitem.client.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Override
    public void save(Todo todo) {
        todoRepository.save(todo);
    }
}
