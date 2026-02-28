package entity;

import java.util.List;

/**
 * Represents a software developer in the management system.
 * Extends BaseEntity for common entity behavior (Inheritance + Polymorphism).
 */
public class Developer extends BaseEntity {
    private String name;
    private List<String> skills;
    private int salary;

    /**
     * Constructor to initialize a Developer object.
     * 
     * @param id     The unique identifier of the developer.
     * @param name   The name of the developer.
     * @param skills The list of programming skills.
     * @param salary The monthly salary of the developer (integer, min 1000 USD).
     */
    public Developer(String id, String name, List<String> skills, int salary) {
        super(id);
        this.name = name;
        this.skills = skills;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toDisplayRow() {
        return String.format("%-10s | %-20s | %-25s | %d", id, name, skills, salary);
    }
}
