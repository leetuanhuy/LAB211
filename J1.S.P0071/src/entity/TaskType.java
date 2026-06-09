package entity;

/**
 * Represents a task type entity with fixed data.
 * The system has 4 predefined task types:
 * <ul>
 *   <li>1 - Code</li>
 *   <li>2 - Test</li>
 *   <li>3 - Design</li>
 *   <li>4 - Review</li>
 * </ul>
 */
public class TaskType {
    private int id;
    private String name;

    /**
     * Constructs a new TaskType with the specified ID and name.
     *
     * @param id   the task type ID
     * @param name the task type name
     */
    public TaskType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Returns the ID of this task type.
     *
     * @return the task type ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of this task type.
     *
     * @param id the task type ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of this task type.
     *
     * @return the task type name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this task type.
     *
     * @param name the task type name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a string representation of the task type in format "id - name".
     *
     * @return a string like "1 - Code"
     */
    @Override
    public String toString() {
        return id + " - " + name;
    }
}
