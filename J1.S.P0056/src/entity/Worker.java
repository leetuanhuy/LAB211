package entity;

/**
 * Represents a worker with personal and salary information.
 */
public class Worker {

    private String id;
    private String name;
    private int age;
    private double salary;
    private String workLocation;

    /**
     * Constructs a new Worker with the specified details.
     *
     * @param id           the worker's code
     * @param name         the worker's name
     * @param age          the worker's age
     * @param salary       the worker's salary
     * @param workLocation the worker's work location
     */
    public Worker(String id, String name, int age, double salary,
            String workLocation) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.workLocation = workLocation;
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

    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

    @Override
    public String toString() {
        return String.format("%-10s%-20s%-10d%-15.2f%-20s",
                id, name, age, salary, workLocation);
    }
}
