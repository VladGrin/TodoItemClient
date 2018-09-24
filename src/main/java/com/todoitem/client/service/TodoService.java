package com.todoitem.client.service;

import com.todoitem.client.entity.Todo;
import org.springframework.stereotype.Service;

@Service
public interface TodoService {

    void save(Todo todo);
}
