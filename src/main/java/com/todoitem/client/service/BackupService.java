package com.todoitem.client.service;

import com.todoitem.client.entity.Backup;
import org.springframework.stereotype.Service;

@Service
public interface BackupService {
    Iterable<Backup> findAll();

    void save(Backup backup);
}
