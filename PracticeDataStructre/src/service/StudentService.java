/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.util.ArrayList;
import model.Student;

/**
 *
 * @author Admin
 */
public class StudentService {

    private ArrayList<Student> students = new ArrayList<Student>();

    public boolean addStudent(Student s) {
        if (findById(s.getId()) != null) {
            return false;
        }
        students.add(s);
        return true;
    }

    public Student findById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    public boolean deleteStudent(int id) {
        Student s = findById(id);
        if (s != null) {
            students.remove(s);
            return true;
        }
        return false;
    }

    public boolean updateStudent(Student newStudent) {
        Student s = findById(newStudent.getId());
        if (s != null) {
            s.setName(newStudent.getName());
            s.setAge(newStudent.getAge());
            s.setScore(newStudent.getScore());
            return true;

        }
        return false;
    }

    public ArrayList<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

}
