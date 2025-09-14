import java.util.ArrayList;
import java.util.Scanner;

public class TodoListApp {
    private static ArrayList<TodoItem> todoList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    // Helper method to repeat a character
    private static String repeat(char c, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append(c);
        }
        return sb.toString();
    }
    
    // Inner class to represent a todo item with completion status
    static class TodoItem {
        String task;
        boolean completed;
        String priority;
        
        public TodoItem(String task, String priority) {
            this.task = task;
            this.completed = false;
            this.priority = priority;
        }
        
        @Override
        public String toString() {
            String status = completed ? "[X]" : "[ ]";
            return String.format("%s %s (%s)", status, task, priority);
        }
    }
    
    public static void main(String[] args) {
        showWelcomePage();
        
        while (true) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim();
            
            if (input.equalsIgnoreCase("exit") || input.equalsIgnoreCase("quit")) {
                System.out.println("\nGoodbye! Thanks for using the To-Do List App!");
                break;
            } else if (input.equalsIgnoreCase("help") || input.equalsIgnoreCase("commands")) {
                showHelp();
            } else if (input.equalsIgnoreCase("welcome")) {
                showWelcomePage();
            } else if (input.equalsIgnoreCase("list") || input.equalsIgnoreCase("show")) {
                showTodoList();
            } else if (input.toLowerCase().startsWith("add ")) {
                addTodoItem(input);
            } else if (input.toLowerCase().startsWith("remove ") || input.toLowerCase().startsWith("delete ")) {
                removeTodoItem(input);
            } else if (input.toLowerCase().startsWith("complete ") || input.toLowerCase().startsWith("done ")) {
                completeTodoItem(input);
            } else if (input.toLowerCase().startsWith("uncomplete ") || input.toLowerCase().startsWith("undo ")) {
                uncompleteTodoItem(input);
            } else if (input.toLowerCase().startsWith("edit ")) {
                editTodoItem(input);
            } else if (input.toLowerCase().startsWith("priority ")) {
                setPriority(input);
            } else if (input.equalsIgnoreCase("clear")) {
                clearAllTodos();
            } else if (input.equalsIgnoreCase("stats")) {
                showStats();
            } else {
                System.out.println("Unknown command. Type 'help' to see available commands.");
            }
        }
        scanner.close();
    }
    
    private static void showWelcomePage() {
        System.out.println("\n" + repeat('=', 60));
        System.out.println("WELCOME TO THE ADVANCED TO-DO LIST APP!");
        System.out.println(repeat('=', 60));
        System.out.println("\nAVAILABLE COMMANDS:");
        System.out.println("+-----------------------------------------------------------+");
        System.out.println("| Command              | Description                      |");
        System.out.println("+-----------------------------------------------------------+");
        System.out.println("| add <task>           | Add a new todo item              |");
        System.out.println("| list / show          | Display all todo items           |");
        System.out.println("| remove <number>      | Remove todo item by number       |");
        System.out.println("| complete <number>    | Mark todo item as completed      |");
        System.out.println("| uncomplete <number>  | Mark todo item as incomplete     |");
        System.out.println("| edit <number> <text> | Edit existing todo item          |");
        System.out.println("| priority <num> <p>   | Set priority (high/medium/low)   |");
        System.out.println("| clear                | Remove all todo items            |");
        System.out.println("| stats                | Show completion statistics       |");
        System.out.println("| help / commands      | Show this help menu              |");
        System.out.println("| welcome              | Show welcome page again          |");
        System.out.println("| exit / quit          | Exit the application             |");
        System.out.println("+-----------------------------------------------------------+");
        System.out.println("\nTIPS:");
        System.out.println("* Use 'add Buy groceries' to add a new task");
        System.out.println("* Use 'complete 1' to mark the first item as done");
        System.out.println("* Use 'priority 1 high' to set high priority");
        System.out.println("* Use 'edit 1 New task text' to modify a task");
        System.out.println("* Completed items show [X], incomplete items show [ ]");
        System.out.println("\nLet's get organized! Type a command to start:");
        System.out.println(repeat('=', 60));
    }
    
    private static void showHelp() {
        System.out.println("\nCOMMAND REFERENCE:");
        System.out.println("+-----------------------------------------------------------+");
        System.out.println("| add <task>           | Add a new todo item              |");
        System.out.println("| list / show          | Display all todo items           |");
        System.out.println("| remove <number>      | Remove todo item by number       |");
        System.out.println("| complete <number>    | Mark todo item as completed      |");
        System.out.println("| uncomplete <number>  | Mark todo item as incomplete     |");
        System.out.println("| edit <number> <text> | Edit existing todo item          |");
        System.out.println("| priority <num> <p>   | Set priority (high/medium/low)   |");
        System.out.println("| clear                | Remove all todo items            |");
        System.out.println("| stats                | Show completion statistics       |");
        System.out.println("| help / commands      | Show this help menu              |");
        System.out.println("| welcome              | Show welcome page again          |");
        System.out.println("| exit / quit          | Exit the application             |");
        System.out.println("+-----------------------------------------------------------+");
    }
    
    private static void showTodoList() {
        if (todoList.isEmpty()) {
            System.out.println("\nYour to-do list is empty. Add some tasks to get started!");
        } else {
            System.out.println("\nYOUR TO-DO LIST:");
            System.out.println(repeat('-', 50));
            for (int i = 0; i < todoList.size(); i++) {
                System.out.println((i + 1) + ". " + todoList.get(i));
            }
            System.out.println(repeat('-', 50));
        }
    }
    
    private static void addTodoItem(String input) {
        String task = input.substring(4).trim();
        if (!task.isEmpty()) {
            todoList.add(new TodoItem(task, "medium"));
            System.out.println("Added: " + task);
        } else {
            System.out.println("Please provide a to-do item to add.");
        }
    }
    
    private static void removeTodoItem(String input) {
        String command = input.toLowerCase().startsWith("remove ") ? "remove " : "delete ";
        String numStr = input.substring(command.length()).trim();
        try {
            int index = Integer.parseInt(numStr) - 1;
            if (index >= 0 && index < todoList.size()) {
                String removed = todoList.remove(index).task;
                System.out.println("Removed: " + removed);
            } else {
                System.out.println("Invalid number. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid number to remove.");
        }
    }
    
    private static void completeTodoItem(String input) {
        String command = input.toLowerCase().startsWith("complete ") ? "complete " : "done ";
        String numStr = input.substring(command.length()).trim();
        try {
            int index = Integer.parseInt(numStr) - 1;
            if (index >= 0 && index < todoList.size()) {
                todoList.get(index).completed = true;
                System.out.println("Marked as completed: " + todoList.get(index).task);
            } else {
                System.out.println("Invalid number. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid number to complete.");
        }
    }
    
    private static void uncompleteTodoItem(String input) {
        String command = input.toLowerCase().startsWith("uncomplete ") ? "uncomplete " : "undo ";
        String numStr = input.substring(command.length()).trim();
        try {
            int index = Integer.parseInt(numStr) - 1;
            if (index >= 0 && index < todoList.size()) {
                todoList.get(index).completed = false;
                System.out.println("Marked as incomplete: " + todoList.get(index).task);
            } else {
                System.out.println("Invalid number. Please try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please provide a valid number to uncomplete.");
        }
    }
    
    private static void editTodoItem(String input) {
        String[] parts = input.substring(5).trim().split(" ", 2);
        if (parts.length >= 2) {
            try {
                int index = Integer.parseInt(parts[0]) - 1;
                if (index >= 0 && index < todoList.size()) {
                    String oldTask = todoList.get(index).task;
                    todoList.get(index).task = parts[1];
                    System.out.println("Edited: '" + oldTask + "' -> '" + parts[1] + "'");
                } else {
                    System.out.println("Invalid number. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please provide a valid number and new text.");
            }
        } else {
            System.out.println("Usage: edit <number> <new text>");
        }
    }
    
    private static void setPriority(String input) {
        String[] parts = input.substring(9).trim().split(" ");
        if (parts.length >= 2) {
            try {
                int index = Integer.parseInt(parts[0]) - 1;
                String priority = parts[1].toLowerCase();
                if (index >= 0 && index < todoList.size()) {
                    if (priority.equals("high") || priority.equals("medium") || priority.equals("low")) {
                        todoList.get(index).priority = priority;
                        System.out.println("Priority set to " + priority + " for: " + todoList.get(index).task);
                    } else {
                        System.out.println("Priority must be 'high', 'medium', or 'low'.");
                    }
                } else {
                    System.out.println("Invalid number. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please provide a valid number and priority.");
            }
        } else {
            System.out.println("Usage: priority <number> <high/medium/low>");
        }
    }
    
    private static void clearAllTodos() {
        if (todoList.isEmpty()) {
            System.out.println("Your to-do list is already empty.");
        } else {
            int count = todoList.size();
            todoList.clear();
            System.out.println("Cleared " + count + " todo item(s) from your list.");
        }
    }
    
    private static void showStats() {
        if (todoList.isEmpty()) {
            System.out.println("No statistics available - your list is empty.");
        } else {
            int total = todoList.size();
            int completed = 0;
            int highPriority = 0;
            int mediumPriority = 0;
            int lowPriority = 0;
            
            for (TodoItem item : todoList) {
                if (item.completed) completed++;
                switch (item.priority) {
                    case "high": highPriority++; break;
                    case "medium": mediumPriority++; break;
                    case "low": lowPriority++; break;
                }
            }
            
            double completionRate = (double) completed / total * 100;
            
            System.out.println("\nTO-DO LIST STATISTICS:");
            System.out.println(repeat('-', 40));
            System.out.println("Total items: " + total);
            System.out.println("Completed: " + completed + " (" + String.format("%.1f", completionRate) + "%)");
            System.out.println("Remaining: " + (total - completed));
            System.out.println("High priority: " + highPriority);
            System.out.println("Medium priority: " + mediumPriority);
            System.out.println("Low priority: " + lowPriority);
            System.out.println(repeat('-', 40));
        }
    }
}

