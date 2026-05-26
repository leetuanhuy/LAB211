/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package view;

import model.Student;
import service.StudentService;
import utils.Validation;

/**
 *
 * @author Admin
 */
public class Main {

    public static void main(String[] args) {
        StudentService service = new StudentService();
        
        while (true) {
            System.out.println("\n===== STUDENT MANAGEMENT MENU =====");
            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Update Student");
            System.out.println("4. View All Students");
            System.out.println("5. Find Student by ID");
            System.out.println("0. Exit");
            
            int choice = Validation.getInt("Choose (0-5): ", 0, 5);
            
            switch (choice) {
                case 1:
                    Student s = inputStudent();
                    if (service.addStudent(s)) {
                        System.out.println("✓ Add successful!");
                    } else {
                        System.out.println("✗ ID already exists!");
                    }
                    break;
                case 2:
                    int idDel = Validation.getInt("Enter ID to delete: ", 1, Integer.MAX_VALUE);
                    if (service.deleteStudent(idDel)) {
                        System.out.println("✓ Delete successful!");
                    } else {
                        System.out.println("✗ Student not found!");
                    }
                    break;
                case 3:
                    Student sUpdate = inputStudent();
                    if (service.updateStudent(sUpdate)) {
                        System.out.println("✓ Update successful!");
                    } else {
                        System.out.println("✗ Student not found!");
                    }
                    break;
                case 4:
                    System.out.println("\n===== LIST OF STUDENTS =====");
                    for (Student st : service.getAllStudents()) {
                        System.out.println(st);
                    }
                    break;
                case 5:
                    int idFind = Validation.getInt("Enter ID to find: ", 1, Integer.MAX_VALUE);
                    Student found = service.findById(idFind);
                    if (found != null) {
                        System.out.println(found);
                    } else {
                        System.out.println("✗ Student not found!");
                    }
                    break;
                case 0:
                    System.out.println("Program ended!");
                    return;
                default:
                    System.out.println("✗ Invalid choice!");
            }
        }
    }

    public static Student inputStudent() {
        int id = Validation.getInt("Enter ID: ", 1, Integer.MAX_VALUE);
        String name = Validation.getString("Enter Name: ", "✗ Name cannot be empty!");
        int age = Validation.getInt("Enter Age: ", 1, 100);
        double score = Validation.getDouble("Enter Score (0-10): ", 0, 10);
        
        return new Student(id, name, age, score);
    }
}