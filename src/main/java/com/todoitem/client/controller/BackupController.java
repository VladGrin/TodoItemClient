package com.todoitem.client.controller;

import com.todoitem.client.exception.ReceiveException;
import com.todoitem.client.service.BackupHandler;
import com.todoitem.client.service.BackupService;
import com.todoitem.client.service.model.BackupAccounts;
import com.todoitem.client.service.model.ListBackups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class BackupController {

    @Autowired
    private BackupService backupService;
    @Autowired
    private BackupHandler backupHandler;

    @PostMapping("/backups")
    public ResponseEntity<List<BackupAccounts>> addBackup() {

        backupHandler.saveToDB();

        List<BackupAccounts> backups = backupService.findAllBackupId();

        return new ResponseEntity<>(backups, HttpStatus.OK);
    }

    @GetMapping("/backups")
    public ResponseEntity<List<ListBackups>> listBackups() {

        List<ListBackups> list = backupService.findListBackups();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/exports/{backupId}")
    public ResponseEntity<String> exportBackup(@PathVariable Long backupId) {

        String backupById = null;
        try {
            backupById = backupService.findBackupById(backupId);
            return new ResponseEntity<>(backupById, HttpStatus.OK);
        } catch (ReceiveException e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
