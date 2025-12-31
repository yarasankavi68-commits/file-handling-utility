import java.io.*;
import java.util.*;

public class HotelMenu{
    private static final String FILE_NAME = "hotel_menu.txt";
    private static ArrayList<String> menuItems = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadMenuFromFile();
        boolean exit = false;
        while (!exit) {
            displayMenuOptions();
            int choice = getUserChoice();
            switch (choice) {
                case 1:
                    viewMenu();
                    break;
                case 2:
                    addItem();
                    break;
                case 3:
                    saveMenuToFile();
                    break;
                case 4:
                    System.out.println("Exiting program. Goodbye!");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenuOptions() {
        System.out.println("\n--- Hotel Menu Management ---");
        System.out.println("1. View Menu");
        System.out.println("2. Add Menu Item");
        System.out.println("3. Save Menu to File");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice() {
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
        return choice;
    }

    private static void viewMenu() {
        System.out.println("\n--- Current Menu ---");
        if (menuItems.isEmpty()) {
            System.out.println("Menu is empty.");
        } else {
            for (int i = 0; i < menuItems.size(); i++) {
                System.out.println((i + 1) + ". " + menuItems.get(i));
            }
        }
    }

    private static void addItem() {
        System.out.print("Enter new menu item name: ");
        String newItem = scanner.nextLine();
        if (!newItem.isEmpty()) {
            menuItems.add(newItem);
            System.out.println("Item added: " + newItem);
        } else {
            System.out.println("Item name cannot be empty.");
        }
    }

    private static void saveMenuToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String item : menuItems) {
                writer.write(item);
                writer.newLine(); // Writing a new line.
            }
            System.out.println("Menu successfully saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the menu: " + e.getMessage());
        }
    }

    private static void loadMenuFromFile() {
        try {
            File file = new File(FILE_NAME);
            if (file.exists()) {
                Scanner fileReader = new Scanner(file);
                while (fileReader.hasNextLine()) {
                    String data = fileReader.nextLine();
                    if (!data.isEmpty()) {
                        menuItems.add(data);
                    }
                }
                fileReader.close();
                System.out.println("Menu loaded from " + FILE_NAME);
            } else {
                System.out.println("Menu file not found. Starting with an empty menu.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading menu file: " + e.getMessage());
        }
    }
}

