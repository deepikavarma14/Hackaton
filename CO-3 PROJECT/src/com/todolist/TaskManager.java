package com.todolist;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskManager {
    private final List<Task> tasks = new CopyOnWriteArrayList<>();

    public void addTask(String description, LocalDateTime reminder, LocalDateTime dueDate) {
        tasks.add(new Task(description, reminder, dueDate));
    }

    public String removeTask(int index) {
        // Adjust for 1-based indexing when removing a task
        int adjustedIndex = index - 1;
        if (adjustedIndex >= 0 && adjustedIndex < tasks.size()) {
            Task removedTask = tasks.remove(adjustedIndex);
            return "Task removed: " + removedTask.description;
        } else {
            return "Invalid index. No task removed.";
        }
    }

    public String viewTasks() {
        if (tasks.isEmpty()) {
            return "No tasks available.";
        }

        StringBuilder taskList = new StringBuilder("Current tasks:\n");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");  // Updated date format

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            taskList.append(i + 1).append(": ")  // Display 1-based index to user
                    .append(task.description);

            if (task.dueDate != null) {
                taskList.append(" | Due: ").append(task.dueDate.format(formatter));
            }
            if (task.reminder != null) {
                taskList.append(" | Reminder: ").append(task.reminder.format(formatter));
            }

            taskList.append("\n");
        }
        return taskList.toString();
    }

    private static class Task {
        String description;
        LocalDateTime reminder;
        LocalDateTime dueDate;

        Task(String description, LocalDateTime reminder, LocalDateTime dueDate) {
            this.description = description;
            this.reminder = reminder;
            this.dueDate = dueDate;
        }
    }
}
