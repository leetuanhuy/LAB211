package entity;

/**
 * Represents a salary adjustment history record for a worker.
 */
public class SalaryHistory {

    private String id;
    private String name;
    private int age;
    private double salary;
    private String status;
    private String date;

    public SalaryHistory() {
    }

    /**
     * Constructs a new SalaryHistory record.
     *
     * @param id     the worker's code
     * @param name   the worker's name
     * @param age    the worker's age
     * @param salary the adjusted salary
     * @param status "UP" for increase, "DOWN" for decrease
     * @param date   the date of adjustment
     */
    public SalaryHistory(String id, String name, int age, double salary, String status, String date) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.status = status;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return String.format("%-10s%-20s%-10d%-15.2f%-10s%-15s",
                id, name, age, salary, status, date);
    }
}
