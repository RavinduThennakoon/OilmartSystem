/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ravin
 */
public class AddUserRole {
    private String position,username,password;
    
    public AddUserRole(String position, String username, String password){
        this.position = position;
        this.username = username;
        this.password = password; 
    }
    
    //getters
    public String getPosition(){
        return position;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    
    //setters
    public void setPosition(String position){
        this.position = position;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
}
