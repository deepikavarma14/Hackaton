package com.todolist;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ToDoListApp {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        ExecutorService executor = Executors.newFixedThreadPool(4);

        boolean running = true;

        while (running) {
            System.out.println("\n🔹 My To-Do List  🔹");
            System.out.println("1. Add a Task");
            System.out.println("2. Remove a Task");
            System.out.println("3. View All Tasks");
            System.out.println("4. Exit");
            System.out.print("What would you like to do: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("\nEnter the task description: ");
                    String description = scanner.nextLine();

                    LocalDateTime dueDate = null;
                    LocalDateTime reminder = null;

                    // Set due date first
                    System.out.print("Enter due date (yyyy-MM-dd HH:mm) or press Enter to skip: ");
                    String dueDateInput = scanner.nextLine().trim();
                    if (!dueDateInput.isEmpty()) {
                        try {
                            dueDate = LocalDateTime.parse(dueDateInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date format. Skipping due date.");
                        }
                    }

                    // Set reminder after due date
                    System.out.print("Enter reminder (yyyy-MM-dd HH:mm) or press Enter to skip: ");
                    String reminderInput = scanner.nextLine().trim();
                    if (!reminderInput.isEmpty()) {
                        try {
                            reminder = LocalDateTime.parse(reminderInput, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date format. Skipping reminder.");
                        }
                    }

                    // Add task to manager
                    LocalDateTime finalDueDate = dueDate;
                    LocalDateTime finalReminder = reminder;
                    executor.execute(() -> taskManager.addTask(description, finalReminder, finalDueDate));
                    break;

                case 2:
                    System.out.print("\nEnter the task index to remove: ");
                    int index;
                    try {
                        index = Integer.parseInt(scanner.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("\nInvalid input. Please enter a valid number.");
                        break;
                    }
                    executor.execute(() -> System.out.println(taskManager.removeTask(index)));
                    break;

                case 3:
                    executor.execute(() -> System.out.println(taskManager.viewTasks()));
                    break;

                case 4:
                    System.out.println("\nExiting application...");
                    running = false;
                    executor.shutdown();
                    break;

                default:
                    System.out.println("\nInvalid option. Try again.");
                    break;
            }
        }

        scanner.close();
    }
}
