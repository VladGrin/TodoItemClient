package com.todoitem.client.service;

import com.todoitem.client.entity.Backup;
import com.todoitem.client.exception.ConnectingException;
import org.springframework.stereotype.Service;


import javax.ws.rs.core.Response;
import java.util.List;

@Service
public interface BackupHandler {

    void saveToDB();

    Backup getBackup() throws ConnectingException;


}
