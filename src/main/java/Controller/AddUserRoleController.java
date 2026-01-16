/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.AddUserRole;
import Model.AddUserRoleDAO;

/**
 *
 * @author ravin
 */
public class AddUserRoleController {
     private final AddUserRoleDAO dao;
 
 public AddUserRoleController(){
     this.dao = new AddUserRoleDAO();
 }
 
 public boolean addRecord(String position, String username, String password){
     position = position == null ? "" : position.trim();
      username = username == null ? "" : username.trim();
       password = password == null ? "" : password.trim();
       
       if(position.isEmpty() && username.isEmpty() && password.isEmpty()){
           return false;
       }
       AddUserRole record = new AddUserRole(position, username, password);
       return dao.addUser(record);
 }
    
}
