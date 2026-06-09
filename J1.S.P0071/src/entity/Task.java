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

    
    public int getId() {
        return id;
    }

    
    public void setId(int id) {
        this.id = id;
    }

    public int getTaskTypeID() {
        return taskTypeID;
    }

    public void setTaskTypeID(int taskTypeID) {
        this.taskTypeID = taskTypeID;
    }

    
    public String getRequirementName() {
        return requirementName;
    }

    
    public void setRequirementName(String requirementName) {
        this.requirementName = requirementName;
    }

   
    public String getDate() {
        return date;
    }

    
    public void setDate(String date) {
        this.date = date;
    }

    
    public double getPlanFrom() {
        return planFrom;
    }

    
    public void setPlanFrom(double planFrom) {
        this.planFrom = planFrom;
    }

    
    public double getPlanTo() {
        return planTo;
    }

    
    public void setPlanTo(double planTo) {
        this.planTo = planTo;
    }

    
    public String getAssignee() {
        return assignee;
    }

    
    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    
    public String getReviewer() {
        return reviewer;
    }

    
    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getTaskTypeName() {
        switch (taskTypeID) {
            case 1: return "Code";
            case 2: return "Test";
            case 3: return "Design";
            case 4: return "Review";
            default: return null;
        }
    }
}
