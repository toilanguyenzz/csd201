package view;

import entity.Developer;
import entity.Project;
import util.Validation;
import java.util.List;
import java.util.Map;

/**
 * Handles all user interface interactions via the console.
 * Responsible for displaying menus, lists, and getting user input.
 */
public class ConsoleView {

    /**
     * Displays the main menu options (12 functions as per Lab211).
     */
    public void displayMenu() {
        System.out.println("\n========== SOFTWARE DEVELOPER MANAGEMENT ==========");
        System.out.println("1.  List all Developers");
        System.out.println("2.  Add a new Developer");
        System.out.println("3.  Search for a Developer by ID");
        System.out.println("4.  Update a Developer's salary by ID");
        System.out.println("5.  List all Developers by Language");
        System.out.println("6.  Add a new Project");
        System.out.println("7.  List all Projects by Developer (Grouped)");
        System.out.println("8.  Calculate Total Experience by Dev ID");
        System.out.println("9.  Remove a Developer by ID");
        System.out.println("10. Sort Developers by Salary");
        System.out.println("11. Save data to files");
        System.out.println("12. Quit program");
        System.out.println("====================================================");
        System.out.print("Choose an option (1-12): ");
    }

    /**
     * Gets an integer input from the user.
     * 
     * @return The integer entered by the user.
     */
    public int getUserChoice() {
        return Validation.checkInputInt();
    }

    /**
     * Displays the developer list in a formatted table.
     * 
     * @param developers List of developers to display.
     */
    public void displayDeveloperList(List<Developer> developers) {
        System.out.println("\n--- Developer List ---");
        System.out.printf("%-10s | %-25s | %-30s | %s%n", "ID", "Name", "Languages", "Salary (USD)");
        System.out.println("--------------------------------------------------------------------------------");
        if (developers.isEmpty()) {
            System.out.println("No developers found.");
        } else {
            for (Developer dev : developers) {
                System.out.println(dev);
            }
        }
    }

    /**
     * Displays the project list in a formatted table.
     * 
     * @param projects List of projects to display.
     */
    public void displayProjectList(List<Project> projects) {
        System.out.println("\n--- Project List ---");
        System.out.printf("%-10s | %-10s | %-25s | %-10s | %s%n", "ID", "Dev ID", "Name", "Duration", "Start Date");
        System.out.println("--------------------------------------------------------------------------------");
        if (projects.isEmpty()) {
            System.out.println("No projects found.");
        } else {
            for (Project proj : projects) {
                System.out.println(proj);
            }
        }
    }

    /**
     * Displays projects grouped by developer.
     * 
     * @param developerProjects Map of Developer to their projects.
     */
    public void displayProjectsByDeveloper(Map<Developer, List<Project>> developerProjects) {
        System.out.println("\n--- Projects by Developer (Grouped) ---");
        if (developerProjects.isEmpty()) {
            System.out.println("No data found.");
            return;
        }
        for (Map.Entry<Developer, List<Project>> entry : developerProjects.entrySet()) {
            Developer dev = entry.getKey();
            List<Project> projects = entry.getValue();
            System.out.println("\n[" + dev.getId() + "] " + dev.getName());
            if (projects.isEmpty()) {
                System.out.println("   (No projects assigned)");
            } else {
                System.out.printf("   %-10s | %-25s | %-10s | %s%n", "Proj ID", "Name", "Duration", "Start Date");
                for (Project proj : projects) {
                    System.out.printf("   %-10s | %-25s | %d months  | %s%n",
                            proj.getId(), proj.getName(), proj.getDurationMonths(), proj.getStartDate());
                }
            }
        }
    }

    /**
     * Displays a single developer's information.
     * 
     * @param dev Developer to display.
     */
    public void displayDeveloper(Developer dev) {
        System.out.println("\n--- Developer Found ---");
        System.out.printf("%-10s | %-25s | %-30s | %s%n", "ID", "Name", "Languages", "Salary (USD)");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println(dev);
    }

    /**
     * Displays a list of developers (for selection menus).
     * 
     * @param developers List of developers.
     */
    public void displayDeveloperMenu(List<Developer> developers) {
        System.out.println("\n--- Select a Developer ---");
        for (int i = 0; i < developers.size(); i++) {
            Developer dev = developers.get(i);
            System.out.printf("%d. [%s] %s%n", i + 1, dev.getId(), dev.getName());
        }
    }

    // ==================== INPUT PROMPTS ====================

    public String getDeveloperIdInput() {
        System.out.print("Enter Developer ID (DEVxxx): ");
        return Validation.checkDevId();
    }

    public String getDeveloperIdSearchInput() {
        System.out.print("Enter Developer ID to search: ");
        return Validation.checkInputString().toUpperCase();
    }

    public String getDeveloperNameInput() {
        System.out.print("Enter Full Name (at least 2 words): ");
        return Validation.checkFullName();
    }

    public String getSkillsInput() {
        System.out.print("Enter Programming Languages (comma separated, e.g., Java, Python): ");
        return Validation.checkInputString();
    }

    public int getSalaryInput() {
        System.out.print("Enter Salary (minimum 1000 USD): ");
        return Validation.checkSalary();
    }

    public String getLanguageSearchInput() {
        System.out.print("Enter Programming Language to search (e.g., Java): ");
        return Validation.checkInputString();
    }

    public String getProjectIdInput() {
        System.out.print("Enter Project ID: ");
        return Validation.checkProjectId();
    }

    public String getProjectNameInput() {
        System.out.print("Enter Project Name: ");
        return Validation.checkInputString();
    }

    public int getDurationInput() {
        System.out.print("Enter Duration (months, minimum 1): ");
        return Validation.checkDuration();
    }

    public String getStartDateInput() {
        System.out.print("Enter Start Date (dd/MM/yyyy, must be in future): ");
        return Validation.checkFutureDate();
    }

    public int getDeveloperSelection(int max) {
        System.out.print("Select Developer (1-" + max + "): ");
        while (true) {
            int choice = Validation.checkInputInt();
            if (choice >= 1 && choice <= max) {
                return choice;
            }
            System.err.println("Please select a valid option!");
            System.out.print("Select again: ");
        }
    }

    public boolean getSaveConfirmation() {
        System.out.print("Do you want to save the changes before exiting? (Y/N): ");
        return Validation.checkInputYN();
    }

    public String getInput(String prompt) {
        System.out.print(prompt);
        return Validation.checkInputScanner();
    }

    // ==================== MESSAGES ====================

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayError(String message) {
        System.err.println(message);
    }

    public void displayTotalExperience(String devId, int totalMonths) {
        System.out.println("\n--- Total Experience ---");
        System.out.println("Developer ID: " + devId);
        System.out.println("Total Experience: " + totalMonths + " months");
    }

    public void displaySaveSuccess() {
        System.out.println("Data saved successfully to developers.txt and projects.txt!");
    }
}
