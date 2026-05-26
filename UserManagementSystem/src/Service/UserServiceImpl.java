/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.User;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class UserServiceImpl extends BaseService implements UserService{

     private ArrayList<User> users = new ArrayList<User>();
    
    @Override
    public void createUser(int id, String name, String role) {
            User u = new User(id, name, role);
            users.add(u);
            log("Created" + name);
    }

    @Override
    public void deleteUser(int id) {
        for(User u : users){
            if(u.getId() == id){
            users.remove(u);
            log("User deleted" + id);
            return;
            }
            System.out.println("User not found");
        }
        
    }
    
   

    @Override
    public void printAllUser() {
        for(User u : users){
         System.out.println("ID: " + u.getId() +
                 "-Name: " + u.getName() + 
                 "-Role: " + u.getRole());
        }
    }

    @Override
    public void searchByName(String keyWord) {
        for(User u : users){
        if(u.getName().toLowerCase().contains(keyWord.toLowerCase())){
        
            System.out.println("ID:" + u.getId()+
                    " Name: " + u.getName());
        
        
        }
        
        }
    }
    
}
