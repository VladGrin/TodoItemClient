package com.todoitem.client.service.impl;

import com.todoitem.client.entity.Backup;
import com.todoitem.client.repository.BackupRepository;
import com.todoitem.client.service.BackupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BackupServiceImpl implements BackupService {
    @Autowired
    private BackupRepository backupRepository;


    @Override
    public Iterable<Backup> findAll() {
        return backupRepository.findAll();
    }

    @Override
    public void save(Backup backup) {
        backupRepository.save(backup);
    }
}
