package com.todoitem.client.formatter.impl;

import com.todoitem.client.entity.Backup;
import com.todoitem.client.entity.Todo;
import com.todoitem.client.entity.User;
import com.todoitem.client.formatter.ResponseFormatter;

public class CSVResponseFormatter implements ResponseFormatter {
    private Backup backup;

    public CSVResponseFormatter(Backup backup) {
        this.backup = backup;
    }

    @Override
    public String getCSVFormat() {
        String csvText = "";
        for (User user : backup.getUsers()) {
            for (Todo todo : user.getTodos()) {
                csvText += "Username;TodoItemId;Subject;DueDate;Done\n" +
                        user.getUsername() + ";" + todo.getId() + ";" + todo.getSubject() +
                        ";" + todo.getDueDate() + ";" + todo.isDone() + "\n";
            }
        }
        return csvText;
    }
}
