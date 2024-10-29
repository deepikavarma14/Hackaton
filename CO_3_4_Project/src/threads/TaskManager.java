package threads;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TaskManager {
    private final List<String> tasks = new CopyOnWriteArrayList<>();

    public String addTask(String task) {
        tasks.add(task);
        return "Task added: " + task;
    }

    public String removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            String removedTask = tasks.remove(index);
            return "Task removed: " + removedTask;
        } else {
            return "Invalid index. No task removed.";
        }
    }
    public List<String> getTasks() {
        return tasks;
    }
    public String viewTasks() {
        if (tasks.isEmpty()) {
            return "No tasks available.";
        }

        StringBuilder taskList = new StringBuilder("Current tasks:<br>");
        for (int i = 0; i < tasks.size(); i++) {
            taskList.append(i).append(": ").append(tasks.get(i)).append("<br>");
        }
        return taskList.toString();
    }
}
