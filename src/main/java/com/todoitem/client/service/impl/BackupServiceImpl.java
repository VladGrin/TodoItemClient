package com.todoitem.client.service.impl;

import com.todoitem.client.entity.Backup;
import com.todoitem.client.entity.Todo;
import com.todoitem.client.entity.User;
import com.todoitem.client.repository.BackupRepository;
import com.todoitem.client.service.BackupService;
import com.todoitem.client.service.model.BackupAccounts;
import com.todoitem.client.service.model.ListBackups;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BackupServiceImpl implements BackupService {

    private final static Logger LOGGER = LoggerFactory.getLogger(MainServerReaderImpl.class);
    @Autowired
    private BackupRepository backupRepository;

    @Override
    public List<BackupAccounts> findAllBackupId() {
        Iterable<Backup> backups = backupRepository.findAll();
        List<BackupAccounts> backupAccounts = new ArrayList<>();
        for (Backup backup : backups){
            BackupAccounts backupAccount = new BackupAccounts(backup.getId());
            LOGGER.info("BackupAccount recieved. BackupAccount details: " + backupAccount);
            backupAccounts.add(backupAccount);
        }
        return backupAccounts;
    }

    @Override
    public List<ListBackups> findListBackups() {
        Iterable<Backup> backups = backupRepository.findAll();
        List<ListBackups> listBackups = new ArrayList<>();
        for (Backup backup : backups){
            ListBackups listBackup = new ListBackups(backup.getId(), backup.getDate(), backup.getStatus());
            LOGGER.info("ListBackup recieved. ListBackup details: " + listBackup);
            listBackups.add(listBackup);
        }
        return listBackups;
    }

    @Override
    public String findBackupById(Long backupId) {
        Backup backupById = backupRepository.findBackupById(backupId);
        String text = "";
        for (User user : backupById.getUsers()) {
            for (Todo todo : user.getTodos()) {
                text += "Username;TodoItemId;Subject;DueDate;Done\n" +
                        user.getUsername() + ";" + todo.getId() + ";" + todo.getSubject() +
                        ";" + todo.getDueDate() + ";" + todo.isDone() + "\n";
            }
        }
        LOGGER.info("Backup " + backupId + " recieved. Format CSV. Backup details: " + text);
        return text;
    }
}
