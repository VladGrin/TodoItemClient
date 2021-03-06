package com.todoitem.client.service.impl;

import com.todoitem.client.entity.Backup;
import com.todoitem.client.entity.Todo;
import com.todoitem.client.entity.User;
import com.todoitem.client.exception.NoContentException;
import com.todoitem.client.formatter.ResponseFormatter;
import com.todoitem.client.formatter.impl.CSVResponseFormatter;
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

    private final static Logger LOGGER = LoggerFactory.getLogger(BackupServiceImpl.class);
    @Autowired
    private BackupRepository backupRepository;
    private ResponseFormatter formatter;

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
    public String findBackupById(Long backupId) throws NoContentException {
        Backup backupById = backupRepository.findBackupById(backupId);
        if (backupById == null){
             throw new NoContentException("No content");
        }
        formatter = new CSVResponseFormatter(backupById);
        String csvText = formatter.getCSVFormat();
        LOGGER.info("Backup " + backupById + " recieved. Format CSV. Backup details: " + csvText);
        return csvText;
    }
}
