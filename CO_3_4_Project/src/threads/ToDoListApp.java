package threads;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class ToDoListApp {
    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);
        ExecutorService executor = Executors.newFixedThreadPool(4);

        boolean running = true;

        while (running) {
            System.out.println("\nTo-Do List:");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. View All Tasks");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim()); 
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter the task: ");
                    String task = scanner.nextLine(); 
                    executor.execute(() -> taskManager.addTask(task)); 
                case 2:
                    System.out.print("Enter the task index to remove: ");
                    int index;
                    try {
                        index = Integer.parseInt(scanner.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                        break;
                    }
                    executor.execute(() -> taskManager.removeTask(index)); 
                    break;
                case 3:
                    executor.execute(taskManager::viewTasks);
                    break;
                case 4:
                    System.out.println("Exiting application...");
                    running = false;
                    executor.shutdown(); 
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
                    break;
            }
        }

        scanner.close(); 
    }
}
