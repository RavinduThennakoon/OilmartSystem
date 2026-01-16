/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Model.LoginUser;
import Model.LoginUserDAO;
/**
 *
 * @author ravin
 */
public class LoginController {
  
 private final LoginUserDAO dao;
 
 
 public LoginController(){
     this.dao = new LoginUserDAO();
 }
 
 public boolean checkLogin(String position, String username, String password){
 
     return dao.validateUser(position, username, password);
     
 }
 
 
    
}
