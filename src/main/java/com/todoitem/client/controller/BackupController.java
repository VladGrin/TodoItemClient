package com.todoitem.client.controller;

import com.todoitem.client.service.BackupHandler;
import com.todoitem.client.service.BackupService;
import com.todoitem.client.service.model.BackupAccounts;
import com.todoitem.client.service.model.ListBackups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/backups")
public class BackupController {
    @Autowired
    private BackupService backupService;
    @Autowired
    private BackupHandler backupHandler;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<List<BackupAccounts>> addBackup() {

        backupHandler.saveToDB();

        List<BackupAccounts> backups = backupService.findAllBackupId();
        return new ResponseEntity<>(backups, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ListBackups>> listBackups() {
        List<ListBackups> list = backupService.findListBackups();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
