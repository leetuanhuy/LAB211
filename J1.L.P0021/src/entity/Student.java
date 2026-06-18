package entity;

/**
 * Represents a student registration record.
 */
public class Student {
    private String id;
    private String studentName;
    private String semester;
    private String courseName;

    public Student() {
    }

    /**
     * Constructs a new Student with the given details.
     *
     * @param id unique registration ID
     * @param studentName student's full name
     * @param semester semester of enrollment
     * @param courseName course name (Java, .Net, or C/C++)
     */
    public Student(String id, String studentName, String semester, String courseName) {
        this.id = id;
        this.studentName = studentName;
        this.semester = semester;
        this.courseName = courseName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Returns student info formatted as "Name | Semester | Course".
     *
     * @return formatted string
     */
    @Override
    public String toString() {
        return studentName + " | " + semester + " | " + courseName;
    }
}
