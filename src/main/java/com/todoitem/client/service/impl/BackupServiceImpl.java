package com.todoitem.client.service.impl;

import com.todoitem.client.entity.Backup;
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
}
