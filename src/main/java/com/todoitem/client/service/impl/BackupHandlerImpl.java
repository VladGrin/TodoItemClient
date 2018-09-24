package com.todoitem.client.service.impl;


import com.todoitem.client.entity.Backup;
import com.todoitem.client.entity.Status;
import com.todoitem.client.entity.Todo;
import com.todoitem.client.entity.User;
import com.todoitem.client.exception.ConnectingException;
import com.todoitem.client.service.BackupHandler;
import com.todoitem.client.service.MainServerReader;
import com.todoitem.client.service.TodoService;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class BackupHandlerImpl implements BackupHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(MainServerReaderImpl.class);
    @Autowired
    private TodoService todoService;
    private MainServerReader mainServerReader = new MainServerReaderImpl();
    private Response response;

    @Override
    public Backup getBackup() throws ConnectingException {
        Date date = new Date();
        response = mainServerReader.getResponseFromMainServer();
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
        Backup backup = new Backup(dateFormat.format(date), status, null);
        LOGGER.info("Backup received. Backup details: " + backup);
        return backup;
    }

    @Override
    public void saveToDB() {

        Backup backup = null;
        try {
            backup = getBackup();
        } catch (ConnectingException e) {
            e.printStackTrace();
        }

        String usersList = response.readEntity(String.class);
        ObjectMapper mapper = new ObjectMapper();
        List<User> users = null;
        try {
            users = Arrays.asList(mapper.readValue(usersList, User[].class));
        } catch (IOException e) {
            e.getStackTrace();
        }

        for(User user : users){
            User newUser = new User(user.getId(), user.getUsername(), user.getEmail(), backup, null);
            LOGGER.info("User created. User details: " + newUser);
            for (Todo todo : user.getTodos()) {
                Todo newTodo = new Todo(todo.getId(), todo.getSubject(), todo.getDueDate(),
                        todo.isDone(), newUser);
                LOGGER.info("Todo created. Todo details: " + newTodo);
                todoService.save(newTodo);
            }
        }
    }
}
