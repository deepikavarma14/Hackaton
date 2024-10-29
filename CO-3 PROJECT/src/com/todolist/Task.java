package com.todolist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private String description;
    private LocalDateTime dueDate;
    private LocalDateTime reminder;

    public Task(String description, LocalDateTime dueDate, LocalDateTime reminder) {
        this.description = description;
        this.dueDate = dueDate;
        this.reminder = reminder;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public LocalDateTime getReminder() {
        return reminder;
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return description + " (Due: " + (dueDate != null ? dueDate.format(formatter) : "None") +
               ", Reminder: " + (reminder != null ? reminder.format(formatter) : "None") + ")";
    }
}
