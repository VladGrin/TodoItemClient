package com.todoitem.client.repository;

import com.todoitem.client.entity.Backup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BackupRepository extends CrudRepository<Backup, Integer> {
}
