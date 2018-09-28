package com.todoitem.client.service;

import com.todoitem.client.entity.Backup;
import com.todoitem.client.exception.ConnectionException;
import org.springframework.stereotype.Service;

@Service
public interface BackupHandler {

    void saveToDB();

    Backup getBackup() throws ConnectionException;
}
