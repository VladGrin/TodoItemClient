package com.todoitem.client.service;

import com.todoitem.client.service.model.BackupAccounts;
import com.todoitem.client.service.model.ListBackups;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BackupService {

    List<BackupAccounts> findAllBackupId();

    List<ListBackups> findListBackups();

    String findBackupById(Long id);
}
