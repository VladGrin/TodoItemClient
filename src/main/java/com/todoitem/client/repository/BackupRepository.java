package com.todoitem.client.repository;

import com.todoitem.client.entity.Backup;
import com.todoitem.client.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackupRepository extends CrudRepository<Backup, Long> {
    Backup findBackupById(Long id);
}
