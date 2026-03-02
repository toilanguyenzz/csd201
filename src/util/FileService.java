package util;

import entity.Developer;
import entity.Project;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Service class for handling file I/O operations for Developers and Projects.
 */
public class FileService {

    /**
     * Loads the list of developers from a file.
     * 
     * @param fileName The path to the file.
     * @return A list of Developer objects.
     */
    public static List<Developer> loadDevelopers(String fileName) {
        List<Developer> developers = new ArrayList<>();
        try (
                BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty())
                    continue;
                // Format: DEV001, Nguyen Van A, [Java, C++], 5000
                String[] parts = line.split(", ");
                if (parts.length >= 4) {
                    String id = parts[0].trim();
                    String name = parts[1].trim();

                    // Parse Skills: [Java, C++] -> Remove [] and split
                    String skillsRaw = line.substring(line.indexOf("[") + 1, line.indexOf("]"));
                    List<String> skills = new ArrayList<>();
                    if (!skillsRaw.isEmpty()) {
                        String[] skillParts = skillsRaw.split(",");
                        for (String s : skillParts) {
                            skills.add(s.trim());
                        }
                    }

                    // Salary is the last part
                    int salary = Integer.parseInt(line.substring(line.lastIndexOf(",") + 1).trim());

                    developers.add(new Developer(id, name, skills, salary));
                }
            }
        } catch (IOException | NumberFormatException e) {
            // File might not exist yet or format error, just return empty list
        }
        return developers;
    }

    /**
     * Loads the list of projects from a file.
     * 
     * @param fileName The path to the file.
     * @return A list of Project objects.
     */
    public static List<Project> loadProjects(String fileName) {
        List<Project> projects = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty())
                    continue;
                // Format: PROJ01, DEV001, E-Commerce Platform, 12, 01/01/2026, ClientName
                String[] parts = line.split(", ");
                if (parts.length >= 5) {
                    String id = parts[0].trim();
                    String devId = parts[1].trim();
                    String name = parts[2].trim();
                    int duration = Integer.parseInt(parts[3].trim());
                    String date = parts[4].trim();
                    String clientName = (parts.length >= 6) ? parts[5].trim() : "";
                    projects.add(new Project(id, devId, name, duration, date, clientName));
                }
            }
        } catch (IOException | NumberFormatException e) {
            // File might not exist yet
        }
        return projects;
    }

    /**
     * Saves the list of developers to a file.
     * 
     * @param fileName   The path to the file.
     * @param developers The list of developers to save.
     */
    public static void saveDevelopers(String fileName, List<Developer> developers) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Developer dev : developers) {
                // Format: DEV001, Nguyen Van A, [Java, C++], 5000
                String skills = "[" + String.join(", ", dev.getSkills()) + "]";
                bw.write(String.format("%s, %s, %s, %d", dev.getId(), dev.getName(), skills,
                        dev.getSalary()));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving developers: " + e.getMessage());
        }
    }

    /**
     * Saves the list of projects to a file.
     * 
     * @param fileName The path to the file.
     * @param projects The list of projects to save.
     */
    public static void saveProjects(String fileName, List<Project> projects) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Project proj : projects) {
                // PROJ01, DEV001, E-Commerce Platform, 12, 01/01/2026, ClientName
                bw.write(String.format("%s, %s, %s, %d, %s, %s", proj.getId(), proj.getDevId(), proj.getName(),
                        proj.getDurationMonths(), proj.getStartDate(), proj.getClientName()));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving projects: " + e.getMessage());
        }
    }
}
