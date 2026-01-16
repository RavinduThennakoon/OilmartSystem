/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ravin
 */
public class LoginUserDAO {
    private Connection conn;
    
    public LoginUserDAO(){
        this.conn = DatabaseConnection.connect();
    }
    
    public boolean validateUser(String position, String username, String password)
    {
        try{
            String sql = "SELECT * FROM rollaccess WHERE job_role=? AND username=? AND password=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, position);
            stmt.setString(2, username);
            stmt.setString(3, password);
           
            
            
            ResultSet rs = stmt.executeQuery();
            
            return rs.next();
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }
        
    
    
    
}
