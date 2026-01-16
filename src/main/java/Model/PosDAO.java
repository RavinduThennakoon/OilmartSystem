/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;


/**
 *
 * @author ravin
 */
public class PosDAO {
 

    // === Insert into tbl_transaction and return generated PK ===
    public int addTransaction(Transaction transaction) {
        String sql = "INSERT INTO tbl_transaction (date_time, cashier_id, total_amount, discount) VALUES (?, ?, ?, ?)";
        int transactionId = -1;

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(transaction.getDate().getTime());
            pstmt.setTimestamp(1, sqlTimestamp);
            pstmt.setString(2, transaction.getCashierId());
            pstmt.setDouble(3, transaction.getTotalAmount());
            pstmt.setDouble(4, transaction.getDiscount());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                transactionId = rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionId;
    }

    // === Insert each item using FK ===
    public void addTransactionItem(TransactionItem item, int transactionId) {
        String insertsql = "INSERT INTO transaction_items (transaction_id, item_code, item_name, category, vehicleType, quantity, sellingPrice, total, discount) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
         String updateStockSql = "UPDATE item SET stock_qty = stock_qty - ? WHERE item_code = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement insertStmt = conn.prepareStatement(insertsql);
             PreparedStatement updateStmt = conn.prepareStatement(updateStockSql)) {

           insertStmt.setInt(1, transactionId);
           insertStmt.setInt(2, item.getItemCode());
           insertStmt.setString(3, item.getItemName());
           insertStmt.setString(4, item.getCategory());
           insertStmt.setString(5, item.getVehicleType());
           insertStmt.setInt(6, item.getQty());
           insertStmt.setDouble(7, item.getSellingPrice());
           insertStmt.setDouble(8, item.gettotal());
           insertStmt.setDouble(9, item.getDiscount());

            insertStmt.executeUpdate();

            updateStmt.setInt(1, item.getQty());      // decrease by sold quantity
        updateStmt.setInt(2, item.getItemCode()); // match item_code
        updateStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        
    }
   // Returns the next available order ID
public int getOrderId() {
    int nextId = 1; // default if table empty
    try {
        Connection conn = DatabaseConnection.connect(); // make sure this matches your connection method
        String sql = "SELECT MAX(transaction_id) FROM tbl_transaction";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int maxId = rs.getInt(1);
            if (!rs.wasNull()) {   
                nextId = maxId + 1;
            }
        }
        rs.close();
        stmt.close();
        conn.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return nextId;
}

}

    
    

