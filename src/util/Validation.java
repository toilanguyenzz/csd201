package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Utility class for handling user input validation.
 * All validation methods follow Lab211 constraints.
 */
public class Validation {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    /**
     * Checks for a non-empty string input.
     * 
     * @return the valid string input.
     */
    public static String checkInputString() {
        while (true) {
            String result = scanner.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Input cannot be empty!");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }

    /**
     * Checks for a positive integer input.
     * 
     * @return the valid integer.
     */
    public static int checkInputInt() {
        while (true) {
            try {
                int result = Integer.parseInt(scanner.nextLine().trim());
                if (result < 0)
                    throw new NumberFormatException();
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input a positive number!");
                System.out.print("Enter again: ");
            }
        }
    }

    /**
     * Checks for a positive double input.
     * 
     * @return the valid double.
     */
    public static double checkInputDouble() {
        while (true) {
            try {
                double result = Double.parseDouble(scanner.nextLine().trim());
                if (result < 0)
                    throw new NumberFormatException();
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input a positive number!");
                System.out.print("Enter again: ");
            }
        }
    }

    /**
     * Checks for Yes/No input.
     * 
     * @return true if Yes, false if No.
     */
    public static boolean checkInputYN() {
        while (true) {
            String result = checkInputString();
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }
            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input Y or N!");
            System.out.print("Enter again: ");
        }
    }

    /**
     * Checks for a valid date input in format dd/MM/yyyy.
     * 
     * @return the valid date string.
     */
    public static String checkInputDate() {
        while (true) {
            try {
                String result = checkInputString();
                SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
                df.setLenient(false);
                df.parse(result);
                return result;
            } catch (ParseException e) {
                System.err.println("Please input correct date format: " + DATE_FORMAT);
                System.out.print("Enter again: ");
            }
        }
    }

    /**
     * Reads a raw string line from the scanner.
     * Use this when empty input is allowed.
     * 
     * @return the raw string input.
     */
    public static String checkInputScanner() {
        return scanner.nextLine().trim();
    }

    // ==================== NEW VALIDATION METHODS FOR LAB211 ====================

    /**
     * Validates Developer ID format: DEV followed by exactly 3 digits.
     * Example: DEV001, DEV123
     * 
     * @return valid Developer ID.
     */
    public static String checkDevId() {
        while (true) {
            String id = checkInputString().toUpperCase();
            if (id.matches("DEV\\d{3}")) {
                return id;
            }
            System.err.println("Developer ID must be in format DEVxxx (e.g., DEV001)!");
            System.out.print("Enter again: ");
        }
    }

    /**
     * Validates Full Name: must contain at least 2 words.
     * 
     * @return valid full name.
     */
    public static String checkFullName() {
        while (true) {
            String name = checkInputString();
            String[] words = name.trim().split("\\s+");
            if (words.length >= 2) {
                return name;
            }
            System.err.println("Full Name must contain at least 2 words!");
            System.out.print("Enter again: ");
        }
    }

    /**
     * Validates Salary: must be a positive integer >= 1000 USD.
     * 
     * @return valid salary as integer.
     */
    public static int checkSalary() {
        while (true) {
            try {
                int salary = Integer.parseInt(scanner.nextLine().trim());
                if (salary >= 1000) {
                    return salary;
                }
                System.err.println("Salary must be at least 1000 USD!");
                System.out.print("Enter again: ");
            } catch (NumberFormatException e) {
                System.err.println("Please input a valid integer!");
                System.out.print("Enter again: ");
            }
        }
    }

    /**
     * Validates that a date is in the future.
     * 
     * @return valid future date string.
     */
    public static String checkFutureDate() {
        while (true) {
            try {
                String result = checkInputString();
                SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
                df.setLenient(false);
                Date inputDate = df.parse(result);
                Date today = new Date();
                if (inputDate.after(today)) {
                    return result;
                }
                System.err.println("Start date must be in the future!");
                System.out.print("Enter again: ");
            } catch (ParseException e) {
                System.err.println("Please input correct date format: " + DATE_FORMAT);
                System.out.print("Enter again: ");
            }
        }
    }

    /**
     * Validates Project ID format: non-empty string identifier.
     * 
     * @return valid Project ID (uppercased).
     */
    public static String checkProjectId() {
        while (true) {
            String id = checkInputString().toUpperCase();
            if (id.matches("PROJ\\d{2,}")) {
                return id;
            }
            System.err.println("Project ID must be in format PROJxx (e.g., PROJ01)!");
            System.out.print("Enter again: ");
        }
    }

    /**
     * Validates Duration: must be >= 1 month.
     * 
     * @return valid duration in months.
     */
    public static int checkDuration() {
        while (true) {
            try {
                int duration = Integer.parseInt(scanner.nextLine().trim());
                if (duration >= 1) {
                    return duration;
                }
                System.err.println("Duration must be at least 1 month!");
                System.out.print("Enter again: ");
            } catch (NumberFormatException e) {
                System.err.println("Please input a valid number!");
                System.out.print("Enter again: ");
            }
        }
    }
}
