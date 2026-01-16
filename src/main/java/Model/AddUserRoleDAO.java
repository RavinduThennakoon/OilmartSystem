/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author ravin
 */
public class AddUserRoleDAO {
    public boolean addUser(AddUserRole adduserrole){
        String sql = "INSERT INTO rollaccess (job_role,username,password) VALUES (?,?,?)";
        
        try(Connection conn = DatabaseConnection.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setString(1, adduserrole.getPosition());
             pstmt.setString(2, adduserrole.getUsername());
              pstmt.setString(3, adduserrole.getPassword());
            
              int rowInserted = pstmt.executeUpdate();
              
              return rowInserted > 0;
                    
            
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
            
        }
            
    
}
}
