package service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import entity.Student;
import static constant.AppConstant.MIN_STUDENTS;
import java.util.LinkedHashMap;

/**
 * Handles business logic for student management. Data is stored in-memory.
 */
public class StudentService {

    private final List<Student> students;

    public StudentService() {
        this.students = new ArrayList<>();
        initSampleData();
    }

    private void initSampleData() {
        students.add(new Student("S01", "Nguyen Van A", "Spring2024", "Java"));
        students.add(new Student("S01", "Nguyen Van A", "Fall2024", ".NET"));
        students.add(new Student("S03", "Nguyen Van B", "Spring2024", ".Net"));
        students.add(new Student("S04", "Nguyen Van B", "Fall2024", "Java"));
        students.add(new Student("S05", "Nguyen Van C", "Spring2024", "Java"));
        students.add(new Student("S01", "Nguyen Van A", "Spring2024", "C/C++"));
        students.add(new Student("S01", "Nguyen Van A", "Fall2024", "Java"));
        students.add(new Student("S08", "Nguyen Van B", "Spring2024", ".Net"));
        students.add(new Student("S09", "Nguyen Van B", "Fall2024", "Java"));

    }

    /**
     * Adds a new course registration for a student.
     *
     * @param id student ID
     * @param name student name
     * @param semester semester
     * @param course course name
     * @throws IllegalArgumentException if the combination (id + semester +
     * course) already exists
     */
    public void addStudent(String id, String name, String semester, String course) {
        boolean exists = students.stream()
                .anyMatch(s -> s.getId().equalsIgnoreCase(id)
                && s.getSemester().equalsIgnoreCase(semester)
                && s.getCourseName().equalsIgnoreCase(course));
        if (exists) {
            throw new IllegalArgumentException("This course registration already exists.");
        }
        students.add(new Student(id, name, semester, course));
    }

    /**
     * Searches for students by name (case-insensitive, partial match) and
     * returns the result sorted alphabetically.
     *
     * @param name search keyword
     * @return list of matching students
     * @throws IllegalArgumentException if no student found
     */
    public List<Student> findStudentsByName(String name) {
        List<Student> result = students.stream()
                .filter(s -> s.getStudentName().toLowerCase().contains(name.toLowerCase()))
                .sorted(Comparator.comparing(Student::getStudentName, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
        if (result.isEmpty()) {
            throw new IllegalArgumentException("No student found.");
        }
        return result;
    }

    /**
     * Finds all course registrations of a student by ID (case-insensitive).
     *
     * @param id the student ID
     * @return list of all registrations for that ID
     * @throws IllegalArgumentException if no record found
     */
    public List<Student> findStudentsById(String id) {
        List<Student> result = students.stream()
                .filter(s -> s.getId().equalsIgnoreCase(id))
                .collect(Collectors.toList());
        if (result.isEmpty()) {
            throw new IllegalArgumentException("Student not found.");
        }
        return result;
    }

    /**
     * Updates a specific course registration. Syncs the new name across all
     * records sharing the original ID to keep data consistent, and allows
     * changing the ID.
     *
     * @param id new student ID
     * @param name new student name
     * @param semester new semester
     * @param course new course name
     * @param targetRecord the specific registration record to update
     * @throws IllegalArgumentException if the combination (id + semester +
     * course) already exists in another record
     */
    public void updateStudent(String id, String name, String semester, String course, Student targetRecord) {
        boolean duplicate = students.stream()
                .anyMatch(s -> s.getId().equalsIgnoreCase(id)
                && s.getSemester().equalsIgnoreCase(semester)
                && s.getCourseName().equalsIgnoreCase(course)
                && s != targetRecord);
        if (duplicate) {
            throw new IllegalArgumentException("This course registration already exists.");
        }
        String currentId = targetRecord.getId();
        students.stream()
                .filter(s -> s.getId().equalsIgnoreCase(currentId))
                .forEach(s -> s.setStudentName(name));
        targetRecord.setId(id);
        targetRecord.setSemester(semester);
        targetRecord.setCourseName(course);
    }

    /**
     * Deletes a specific course registration record.
     *
     * @param targetRecord the registration record to delete
     * @throws IllegalArgumentException if the record does not exist
     */
    public void deleteStudent(Student targetRecord) {
        if (!students.remove(targetRecord)) {
            throw new IllegalArgumentException("Student not found.");
        }
    }

    /**
     * Groups registrations by student (via ID) and course, then counts total
     * enrollments. Key format: "ID#StudentName | CourseName" to distinguish
     * students with same name.
     *
     * @return map of "ID#name | course" to total count
     */
//    public Map<String, Long> generateReport() {
//        return students.stream()
//                .collect(Collectors.groupingBy(
//                        s -> s.getId() + "#" + s.getStudentName() + " | " + s.getCourseName(),
//                        LinkedHashMap::new,
//                        Collectors.counting()));
//    }
    public Map<String, Long> generateReport() {
        // 1. Tạo một cái Map rỗng để chứa kết quả báo cáo
        Map<String, Long> reportMap = new LinkedHashMap<>();

        // 2. Dùng vòng lặp duyệt qua từng sinh viên một trong danh sách 'students'
        for (Student s : students) {
            // Tạo cái nhãn Key giống hệt như cách làm của Stream: "ID#Tên | Môn"
            String key = s.getId() + "#" + s.getStudentName() + " | " + s.getCourseName();

            // 3. Kiểm tra xem cái nhãn Key này đã từng xuất hiện trong bản báo cáo chưa
            if (reportMap.containsKey(key)) {
                // Nếu ĐÃ CÓ RỒI: Lấy số lượng cũ ra, cộng thêm 1, rồi lưu đè lại
                long currentCount = reportMap.get(key);
                reportMap.put(key, currentCount + 1);
            } else {
                // Nếu CHƯA CÓ (lần đầu tiên gặp người này học môn này): Đặt số lượng là 1
                reportMap.put(key, 1L);
            }
        }

        // 4. Trả về bảng báo cáo đã gom nhóm và đếm xong
        return reportMap;
    }

    /**
     * Checks whether the student list has reached the minimum required count.
     *
     * @return true if total students >= minimum threshold
     */
    public boolean hasReachedMinStudents() {
        return students.size() >= MIN_STUDENTS;
    }

}
