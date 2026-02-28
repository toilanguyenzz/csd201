package entity;

/**
 * Abstract base class for all identifiable entities in the system.
 * Provides common id field and display contract.
 * Demonstrates OOP principles: Abstraction and Inheritance.
 */
public abstract class BaseEntity {
    protected String id;

    /**
     * Constructor for BaseEntity.
     * 
     * @param id The unique identifier.
     */
    public BaseEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns a formatted string for table display.
     * Subclasses must implement their own display format.
     * 
     * @return formatted display string.
     */
    public abstract String toDisplayRow();

    @Override
    public String toString() {
        return toDisplayRow();
    }
}
