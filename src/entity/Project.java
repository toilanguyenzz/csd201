package entity;

/**
 * Represents a project in the management system.
 * Extends BaseEntity for common entity behavior (Inheritance + Polymorphism).
 */
public class Project extends BaseEntity {
    private String devId;
    private String name;
    private int durationMonths;
    private String startDate;

    /**
     * Constructor to initialize a Project object.
     * 
     * @param id             The unique identifier of the project.
     * @param devId          The ID of the developer assigned to this project.
     * @param name           The name of the project.
     * @param durationMonths The duration of the project in months.
     * @param startDate      The start date of the project (dd/MM/yyyy).
     */
    public Project(String id, String devId, String name, int durationMonths, String startDate) {
        super(id);
        this.devId = devId;
        this.name = name;
        this.durationMonths = durationMonths;
        this.startDate = startDate;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(int durationMonths) {
        this.durationMonths = durationMonths;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toDisplayRow() {
        return String.format("%-10s | %-10s | %-20s | %d months | %s", id, devId, name, durationMonths, startDate);
    }
}
