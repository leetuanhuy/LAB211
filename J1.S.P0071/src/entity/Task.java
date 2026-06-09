package entity;

/**
 * Represents a task entity in the task management system.
 * Each task contains information about the work to be done including
 * the assignee, reviewer, time schedule, and task type.
 */
public class Task {
    private int id;
    private int taskTypeID;
    private String requirementName;
    private String date;
    private double planFrom;
    private double planTo;
    private String assignee;
    private String reviewer;

    /**
     * Constructs a new Task with the specified details.
     *
     * @param id              the unique identifier for the task
     * @param taskTypeID      the type of task (1=Code, 2=Test, 3=Design, 4=Review)
     * @param requirementName the name of the requirement
     * @param date            the date of the task in dd-MM-yyyy format
     * @param planFrom        the start time (8.0 to 17.5, 0.5 increments)
     * @param planTo          the end time (8.0 to 17.5, 0.5 increments)
     * @param assignee        the person assigned to the task
     * @param reviewer        the person reviewing the task
     */
    public Task(int id, int taskTypeID, String requirementName, String date,
                double planFrom, double planTo, String assignee, String reviewer) {
        this.id = id;
        this.taskTypeID = taskTypeID;
        this.requirementName = requirementName;
        this.date = date;
        this.planFrom = planFrom;
        this.planTo = planTo;
        this.assignee = assignee;
        this.reviewer = reviewer;
    }

    /**
     * Returns the unique ID of this task.
     *
     * @return the task ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique ID of this task.
     *
     * @param id the task ID to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the task type ID.
     *
     * @return the task type ID (1=Code, 2=Test, 3=Design, 4=Review)
     */
    public int getTaskTypeID() {
        return taskTypeID;
    }

    /**
     * Sets the task type ID.
     *
     * @param taskTypeID the task type ID to set (1-4)
     */
    public void setTaskTypeID(int taskTypeID) {
        this.taskTypeID = taskTypeID;
    }

    /**
     * Returns the requirement name.
     *
     * @return the requirement name
     */
    public String getRequirementName() {
        return requirementName;
    }

    /**
     * Sets the requirement name.
     *
     * @param requirementName the requirement name to set
     */
    public void setRequirementName(String requirementName) {
        this.requirementName = requirementName;
    }

    /**
     * Returns the date of the task.
     *
     * @return the date in dd-MM-yyyy format
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of the task.
     *
     * @param date the date to set in dd-MM-yyyy format
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Returns the planned start time.
     *
     * @return the start time (8.0 to 17.5)
     */
    public double getPlanFrom() {
        return planFrom;
    }

    /**
     * Sets the planned start time.
     *
     * @param planFrom the start time to set (8.0 to 17.5, 0.5 increments)
     */
    public void setPlanFrom(double planFrom) {
        this.planFrom = planFrom;
    }

    /**
     * Returns the planned end time.
     *
     * @return the end time (8.0 to 17.5)
     */
    public double getPlanTo() {
        return planTo;
    }

    /**
     * Sets the planned end time.
     *
     * @param planTo the end time to set (8.0 to 17.5, 0.5 increments)
     */
    public void setPlanTo(double planTo) {
        this.planTo = planTo;
    }

    /**
     * Returns the assignee of this task.
     *
     * @return the assignee name
     */
    public String getAssignee() {
        return assignee;
    }

    /**
     * Sets the assignee of this task.
     *
     * @param assignee the assignee name to set
     */
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    /**
     * Returns the reviewer of this task.
     *
     * @return the reviewer name
     */
    public String getReviewer() {
        return reviewer;
    }

    /**
     * Sets the reviewer of this task.
     *
     * @param reviewer the reviewer name to set
     */
    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }
}
