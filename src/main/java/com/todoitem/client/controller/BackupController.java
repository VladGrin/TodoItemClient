package com.todoitem.client.controller;

import com.todoitem.client.entity.Backup;
import com.todoitem.client.entity.User;
import com.todoitem.client.exception.ConnectingException;
import com.todoitem.client.service.BackupHandler;
import com.todoitem.client.service.BackupService;
import com.todoitem.client.service.TodoService;
import com.todoitem.client.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/backup")
public class BackupController {
    @Autowired
    private BackupService backupService;
    @Autowired
    private UserService userService;
    @Autowired
    private TodoService todoService;
    @Autowired
    private BackupHandler backupHandler;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Backup>> getBackup(){
        Iterable<Backup> backups = backupService.findAll();
        return new ResponseEntity<>(backups, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addBackup(){
        Backup backup = null;
        try {
            backup = backupHandler.getBackup();
        } catch (ConnectingException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        backupService.save(backup);
        for(User user : backup.getUsers()){
//            User user1 = new User(user.getId(), user.getUsername(), user.getEmail(), user.getTodos());
//            System.out.println(user1);
            userService.save(user);
        }
        return new ResponseEntity<>("save", HttpStatus.OK);
    }


}
