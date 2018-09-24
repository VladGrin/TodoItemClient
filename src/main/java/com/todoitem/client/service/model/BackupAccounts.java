package com.todoitem.client.service.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
public class BackupAccounts {
    private Long backupId;

    public BackupAccounts() {
    }

    public BackupAccounts(Long backupId) {
        this.backupId = backupId;
    }
}
