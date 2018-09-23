package com.todoitem.client.service.impl;


import com.todoitem.client.entity.Backup;
import com.todoitem.client.entity.Status;
import com.todoitem.client.entity.Todo;
import com.todoitem.client.entity.User;
import com.todoitem.client.exception.ConnectingException;
import com.todoitem.client.service.BackupHandler;
import com.todoitem.client.service.MainServerReader;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BackupHandlerImpl implements BackupHandler {

    private MainServerReader mainServerReader = new MainServerReaderImpl();
    private Integer backupId = 0;

    @Override
    public Backup getBackup() throws ConnectingException {
        Date date = new Date();
        Response response = mainServerReader.getResponseFromMainServer();
        List<User> users = findUsersFromMainServer(response);
//        List<Todo> todos = findTodosFromMainServer(users);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Status status = null;
        int responseStatus = response.getStatus();
        if (responseStatus == 200) {
            status = Status.OK;
        } else if (responseStatus >= 400){
            status = Status.FAILED;
        } else {
            status = Status.IN_PROGRESS;
        }
        return new Backup(dateFormat.format(date), status, users);
    }

    @Override
    public List<User> findUsersFromMainServer(Response response) {
        String usersList = null;
        usersList = response.readEntity(String.class);
        ObjectMapper mapper = new ObjectMapper();
        List<User> users = null;
        try {
            users = Arrays.asList(mapper.readValue(usersList, User[].class));
        } catch (IOException e) {
            e.getStackTrace();
        }
        return users;
    }

//    @Override
//    public List<Todo> findTodosFromMainServer(List<User> users) {
//        List<Todo> todos = new ArrayList<>();
//        users.stream().map(x -> todos.addAll(x.getTodos())).collect(Collectors.toList());
//        return todos;
//    }
}
