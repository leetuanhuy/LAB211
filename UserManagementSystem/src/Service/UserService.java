/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

/**
 *
 * @author Admin
 */
public interface UserService {
    void createUser (int id, String name, String role);
    void deleteUser(int id);
    void printAllUser();
    void searchByName(String keyWord);
}
