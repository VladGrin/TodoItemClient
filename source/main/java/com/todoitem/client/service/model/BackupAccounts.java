package com.todoitem.client.service.model;

import lombok.Data;

@Data
public class BackupAccounts {
    private Long backupId;

    public BackupAccounts(Long backupId) {
        this.backupId = backupId;
    }
}
