package com.todoitem.client.service;

import com.todoitem.client.entity.Backup;
import com.todoitem.client.entity.Todo;
import com.todoitem.client.entity.User;
import com.todoitem.client.exception.ConnectingException;
import org.springframework.stereotype.Service;


import javax.ws.rs.core.Response;
import java.util.List;

@Service
public interface BackupHandler {

    Backup getBackup() throws ConnectingException;

    Iterable<User> findUsersFromMainServer(Response response);

//    List<Todo> findTodosFromMainServer(List<User> users);
}
