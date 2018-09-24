package com.todoitem.client.service.impl;

import com.todoitem.client.entity.Backup;
import com.todoitem.client.entity.Todo;
import com.todoitem.client.entity.User;
import com.todoitem.client.repository.BackupRepository;
import com.todoitem.client.service.BackupService;
import com.todoitem.client.service.model.BackupAccounts;
import com.todoitem.client.service.model.ListBackups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BackupServiceImpl implements BackupService {
    @Autowired
    private BackupRepository backupRepository;

    @Override
    public List<BackupAccounts> findAllBackupId() {
        Iterable<Backup> backups = backupRepository.findAll();
        List<BackupAccounts> backupAccounts = new ArrayList<>();
        for (Backup backup : backups){
            backupAccounts.add(new BackupAccounts(backup.getId()));
        }
        return backupAccounts;
    }

    @Override
    public List<ListBackups> findListBackups() {
        Iterable<Backup> backups = backupRepository.findAll();
        List<ListBackups> listBackups = new ArrayList<>();
        for (Backup backup : backups){
            listBackups.add(new ListBackups(backup.getId(), backup.getDate(), backup.getStatus()));
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
//                    LOGGER.info("Backups found. Backup details: user - " + user + ", todo - " + todo);
                }
            }
        return text;
    }
}
