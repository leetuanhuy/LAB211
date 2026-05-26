/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package usermanagementsystem;

import Service.UserService;
import Service.UserServiceImpl;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class UserManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n========Menu========\n");
            System.out.println("1. Create user");
            System.out.println("2. Delete user");
            System.out.println("3. Show all users");
            System.out.println("4. find name users");

            System.out.println("0. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Entet id");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter name:");
                    String name = sc.nextLine();

                    System.out.print("Enter role: ");
                    String role = sc.nextLine();

                    service.createUser(id, name, role);
                    break;
                case 2:
                    System.out.print("Enter id to delete: ");
                    int deleteId = sc.nextInt();
                    service.deleteUser(deleteId);
                    break;

                case 3:
                    service.printAllUser();
                    break;
                case 4:
                    System.out.println("Enter keyword");
                    String keyword = sc.nextLine();
                    service.searchByName(keyword);
                    break;

                case 0:
                    System.out.println("Exit...");
                    return;

                default:
                    System.out.println("Invalid choice");

            }

        }
    }

}
