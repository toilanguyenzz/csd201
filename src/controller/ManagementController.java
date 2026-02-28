package controller;

import entity.Developer;
import entity.Project;
import util.FileService;
import util.Validation;
import view.ConsoleView;

import java.util.*;

/**
 * Controller class managing the application flow and business logic.
 * Implements all 12 menu functions as per Lab211 requirements.
 */
public class ManagementController {
    private List<Developer> developers;
    private List<Project> projects;
    private static final String DEV_FILE = "developers.txt";
    private static final String PROJ_FILE = "projects.txt";
    private ConsoleView view;
    private boolean hasUnsavedChanges = false;

    /**
     * Constructor initializes the controller with a view and loads data.
     */
    public ManagementController() {
        this.developers = FileService.loadDevelopers(DEV_FILE);
        this.projects = FileService.loadProjects(PROJ_FILE);
        this.view = new ConsoleView();
    }

    /**
     * Starts the main application loop with 12 menu options.
     */
    public void run() {
        while (true) {
            view.displayMenu();
            int choice = view.getUserChoice();
            switch (choice) {
                case 1:
                    listAllDevelopers();
                    break;
                case 2:
                    addDeveloper();
                    break;
                case 3:
                    searchDeveloperById();
                    break;
                case 4:
                    updateDeveloperSalary();
                    break;
                case 5:
                    listDevelopersByLanguage();
                    break;
                case 6:
                    addProject();
                    break;
                case 7:
                    listProjectsByDeveloper();
                    break;
                case 8:
                    calculateTotalExperience();
                    break;
                case 9:
                    removeDeveloper();
                    break;
                case 10:
                    sortDevelopersBySalary();
                    break;
                case 11:
                    saveData();
                    break;
                case 12:
                    quitProgram();
                    return;
                default:
                    view.displayError("Invalid option! Please choose 1-12.");
            }
        }
    }

    // ==================== 1. LIST ALL DEVELOPERS ====================
    private void listAllDevelopers() {
        view.displayDeveloperList(developers);
    }

    // ==================== 2. ADD A NEW DEVELOPER ====================
    private void addDeveloper() {
        view.displayMessage("\n--- Add New Developer ---");

        String id = view.getDeveloperIdInput();
        if (findDeveloperById(id) != null) {
            view.displayError("Developer ID already exists!");
            return;
        }

        String name = view.getDeveloperNameInput();
        String skillsInput = view.getSkillsInput();
        List<String> skills = new ArrayList<>();
        for (String s : skillsInput.split(",")) {
            skills.add(s.trim());
        }

        int salary = view.getSalaryInput();

        developers.add(new Developer(id, name, skills, salary));
        hasUnsavedChanges = true;
        view.displayMessage("Developer added successfully!");
    }

    // ==================== 3. SEARCH DEVELOPER BY ID ====================
    private void searchDeveloperById() {
        view.displayMessage("\n--- Search Developer by ID ---");
        String id = view.getDeveloperIdSearchInput();
        Developer dev = findDeveloperById(id);

        if (dev == null) {
            view.displayError("Developer ID does not exist!");
        } else {
            view.displayDeveloper(dev);
        }
    }

    // ==================== 4. UPDATE DEVELOPER'S SALARY ====================
    private void updateDeveloperSalary() {
        view.displayMessage("\n--- Update Developer Salary ---");
        String id = view.getDeveloperIdSearchInput();
        Developer dev = findDeveloperById(id);

        if (dev == null) {
            view.displayError("Developer ID does not exist!");
            return;
        }

        view.displayDeveloper(dev);
        view.displayMessage("\nEnter new salary:");
        int newSalary = view.getSalaryInput();
        dev.setSalary(newSalary);
        hasUnsavedChanges = true;
        view.displayMessage("Developer salary updated successfully!");
    }

    // ==================== 5. LIST DEVELOPERS BY LANGUAGE ====================
    private void listDevelopersByLanguage() {
        view.displayMessage("\n--- List Developers by Language ---");
        String language = view.getLanguageSearchInput();

        List<Developer> filtered = new ArrayList<>();
        for (Developer d : developers) {
            for (String s : d.getSkills()) {
                if (s.equalsIgnoreCase(language)) {
                    filtered.add(d);
                    break;
                }
            }
        }

        if (filtered.isEmpty()) {
            view.displayMessage("No developers found with language: " + language);
        } else {
            view.displayMessage("Developers with " + language + ":");
            view.displayDeveloperList(filtered);
        }
    }

    // ==================== 6. ADD A NEW PROJECT ====================
    private void addProject() {
        view.displayMessage("\n--- Add New Project ---");

        if (developers.isEmpty()) {
            view.displayError("No developers exist! Please add a developer first.");
            return;
        }

        String projId = view.getProjectIdInput();
        if (findProjectById(projId) != null) {
            view.displayError("Project ID already exists!");
            return;
        }

        // Select developer from menu
        view.displayDeveloperMenu(developers);
        int selection = view.getDeveloperSelection(developers.size());
        Developer selectedDev = developers.get(selection - 1);

        String name = view.getProjectNameInput();
        int duration = view.getDurationInput();
        String startDate = view.getStartDateInput();

        projects.add(new Project(projId, selectedDev.getId(), name, duration, startDate));
        hasUnsavedChanges = true;
        view.displayMessage("Project added successfully!");
    }

    // ==================== 7. LIST PROJECTS BY DEVELOPER (GROUPED)
    // ====================
    private void listProjectsByDeveloper() {
        Map<Developer, List<Project>> grouped = new LinkedHashMap<>();

        for (Developer dev : developers) {
            List<Project> devProjects = new ArrayList<>();
            for (Project p : projects) {
                if (p.getDevId().equalsIgnoreCase(dev.getId())) {
                    devProjects.add(p);
                }
            }
            grouped.put(dev, devProjects);
        }

        view.displayProjectsByDeveloper(grouped);
    }

    // ==================== 8. CALCULATE TOTAL EXPERIENCE ====================
    private void calculateTotalExperience() {
        view.displayMessage("\n--- Calculate Total Experience ---");
        String id = view.getDeveloperIdSearchInput();
        Developer dev = findDeveloperById(id);

        if (dev == null) {
            view.displayError("Developer ID does not exist!");
            return;
        }

        int totalMonths = 0;
        for (Project p : projects) {
            if (p.getDevId().equalsIgnoreCase(id)) {
                totalMonths += p.getDurationMonths();
            }
        }

        view.displayTotalExperience(id, totalMonths);
    }

    // ==================== 9. REMOVE DEVELOPER BY ID ====================
    private void removeDeveloper() {
        view.displayMessage("\n--- Remove Developer ---");
        String id = view.getDeveloperIdSearchInput();
        Developer dev = findDeveloperById(id);

        if (dev == null) {
            view.displayError("Developer ID does not exist!");
            return;
        }

        // Check if developer has associated projects
        boolean hasProjects = false;
        for (Project p : projects) {
            if (p.getDevId().equalsIgnoreCase(id)) {
                hasProjects = true;
                break;
            }
        }

        if (hasProjects) {
            view.displayError("Cannot delete: Developer is assigned to projects.");
            return;
        }

        developers.remove(dev);
        hasUnsavedChanges = true;
        view.displayMessage("Developer removed successfully!");
    }

    // ==================== 10. SORT DEVELOPERS BY SALARY ====================
    private void sortDevelopersBySalary() {
        view.displayMessage("\n--- Developers Sorted by Salary (Ascending) ---");
        List<Developer> sorted = new ArrayList<>(developers);
        Collections.sort(sorted, Comparator.comparingDouble(Developer::getSalary));
        view.displayDeveloperList(sorted);
    }

    // ==================== 11. SAVE DATA TO FILES ====================
    private void saveData() {
        FileService.saveDevelopers(DEV_FILE, developers);
        FileService.saveProjects(PROJ_FILE, projects);
        hasUnsavedChanges = false;
        view.displaySaveSuccess();
    }

    // ==================== 12. QUIT PROGRAM ====================
    private void quitProgram() {
        if (hasUnsavedChanges) {
            boolean save = view.getSaveConfirmation();
            if (save) {
                saveData();
            }
        }
        view.displayMessage("Goodbye!");
    }

    // ==================== HELPER METHODS ====================
    private Developer findDeveloperById(String id) {
        for (Developer d : developers) {
            if (d.getId().equalsIgnoreCase(id)) {
                return d;
            }
        }
        return null;
    }

    private Project findProjectById(String id) {
        for (Project p : projects) {
            if (p.getId().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }
}
