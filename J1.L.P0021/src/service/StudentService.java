package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import entity.Student;
import repository.StudentRepository;
import utils.Validtion;

/**
 * Provides business logic for student management operations.
 */
public class StudentService {
    private static final int MIN_STUDENTS = 10;
    private static final String[] VALID_COURSES = {"Java", ".Net", "C/C++"};

    private final StudentRepository repository;

    public StudentService() {
        this.repository = new StudentRepository();
        initSampleData();
    }

    private void initSampleData() {
        repository.add(new Student("S01", "Nguyen Van A", "Spring2024", "Java"));
        repository.add(new Student("S02", "Nguyen Van A", "Fall2024", "Java"));
        repository.add(new Student("S03", "Nguyen Van B", "Spring2024", ".Net"));
        repository.add(new Student("S04", "Nguyen Van B", "Fall2024", "Java"));
        repository.add(new Student("S05", "Nguyen Van C", "Spring2024", "Java"));
        repository.add(new Student("S06", "Nguyen Van C", "Fall2024", "C/C++"));
        repository.add(new Student("S07", "Tran Van D", "Spring2024", ".Net"));
        repository.add(new Student("S08", "Le Van E", "Fall2024", "Java"));
        repository.add(new Student("S09", "Pham Van F", "Spring2024", "C/C++"));
    }

    /**
     * Creates new students. Loops until the user chooses to stop after
     * reaching the minimum of 10 students.
     */
    public void createStudent() {
        while (true) {
            String id = getUniqueId();
            String name = Validtion.getString("Enter Student Name: ", "Name cannot be empty.");
            String semester = Validtion.getString("Enter Semester: ", "Semester cannot be empty.");
            String course = getCourse();

            repository.add(new Student(id, name, semester, course));
            System.out.println("Student added successfully.");

            if (repository.getTotal() >= MIN_STUDENTS) {
                if (!Validtion.getYN("Do you want to continue (Y/N)? ")) {
                    break;
                }
            }
        }
    }

    /**
     * Searches students by name (partial match) and displays results
     * sorted alphabetically by name.
     */
    public void findAndSort() {
        String name = Validtion.getString("Enter student name to search: ", "Name cannot be empty.");
        List<Student> result = repository.findByName(name);
        if (result.isEmpty()) {
            System.out.println("No student found.");
            return;
        }
        List<Student> sortedList = sortByName(result);
        System.out.println("Student Name | Semester | Course Name");
        for (Student student : sortedList) {
            System.out.println(student);
        }
    }

    /**
     * Finds a student by ID, then offers update or delete options.
     */
    public void updateOrDelete() {
        String id = Validtion.getString("Enter student ID: ", "ID cannot be empty.");
        Student student = repository.findById(id);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        String choice = Validtion.getUD("Do you want to update (U) or delete (D) student? ");
        if (choice.equals("U")) {
            String name = Validtion.getString("Enter new Student Name: ", "Name cannot be empty.");
            String semester = Validtion.getString("Enter new Semester: ", "Semester cannot be empty.");
            String course = getCourse();
            student.setStudentName(name);
            student.setSemester(semester);
            student.setCourseName(course);
            repository.update(student);
            System.out.println("Student updated successfully.");
        } else {
            repository.delete(id);
            System.out.println("Student deleted successfully.");
        }
    }

    /**
     * Displays a report of each student with total enrollments per course.
     * Groups by student name and course name, then counts occurrences.
     */
    public void report() {
        List<Student> allStudents = repository.getAll();
        if (allStudents.isEmpty()) {
            System.out.println("No students in the list.");
            return;
        }
        Map<String, Integer> countMap = new LinkedHashMap<>();
        for (Student student : allStudents) {
            String key = student.getStudentName() + " | " + student.getCourseName();
            countMap.put(key, countMap.getOrDefault(key, 0) + 1);
        }
        System.out.println("Student Name | Course | Total");
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            System.out.println(entry.getKey() + " | " + entry.getValue());
        }
    }

    /**
     * Prompts for a unique ID, re-prompting if the ID already exists.
     *
     * @return a unique student ID
     */
    private String getUniqueId() {
        while (true) {
            String id = Validtion.getString("Enter ID: ", "ID cannot be empty.");
            if (!repository.isIdExist(id)) {
                return id;
            }
            System.out.println("ID already exists. Please enter a different ID.");
        }
    }

    /**
     * Prompts for and validates a course name against the allowed list.
     *
     * @return a valid course name (Java, .Net, or C/C++)
     */
    private String getCourse() {
        while (true) {
            String course = Validtion.getString("Enter Course (Java, .Net, C/C++): ", "Course cannot be empty.");
            for (String validCourse : VALID_COURSES) {
                if (course.equalsIgnoreCase(validCourse)) {
                    return validCourse;
                }
            }
            System.out.println("Invalid course. Please enter Java, .Net, or C/C++.");
        }
    }

    /**
     * Sorts a list of students alphabetically by name.
     *
     * @param list the list to sort
     * @return a new sorted list
     */
    private List<Student> sortByName(List<Student> list) {
        List<Student> sortedList = new ArrayList<>(list);
        Collections.sort(sortedList, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getStudentName().compareToIgnoreCase(s2.getStudentName());
            }
        });
        return sortedList;
    }
}
