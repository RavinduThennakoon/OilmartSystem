/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ravin
 */
public class DashboardDAO {
    public List<Transaction> getAllItems() {
        List<Transaction> transactionList = new ArrayList<>();
        String sql = "SELECT * FROM tbl_transaction";

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Transaction transaction = new Transaction(
                    rs.getTimestamp("date_time"),
                    rs.getString("cashier_id"),
                    rs.getDouble("total_amount"),
                    rs.getDouble("discount")
                   
                   
                   
                );
                
                transactionList.add(transaction); 
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return transactionList;
    }
    
    public ReportModel getTodayProfit(){
        String sql = """
                     SELECT 
                        DATE(t.date_time) AS transaction_date,
                      SUM(t.total_amount) AS dailySales,
                        SUM((ti.sellingPrice - i.cost_price) * ti.quantity) AS dailyprofit
                    FROM tbl_transaction t
                    JOIN transaction_items ti ON t.transaction_id = ti.transaction_id
                    JOIN item i ON ti.item_code = i.item_code
                    WHERE DATE(t.date_time) = CURDATE()
                    GROUP BY DATE(t.date_time);
                     """;
       
        try (Connection conn = DatabaseConnection.connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
            
            while (rs.next()){
                Date date = rs.getDate("transaction_date");
                double profit = rs.getDouble("dailyprofit");
               double dailySales = rs.getDouble("dailySales");
                 System.out.println("Date is "+ date);
                 System.out.println("Profit  is "+ profit);
//                 System.out.println("Daily Sales is "+dailySales);
                return new ReportModel(date, profit, dailySales);
                 
                 
            }
         
            
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
         
        
    }
    public ReportModel getMonthlyProfit(){
        String sql =  """
             SELECT 
                    YEAR(t.date_time) AS transaction_year,
                    MONTH(t.date_time) AS transaction_month,
                    SUM((ti.sellingPrice - i.cost_price) * ti.quantity) AS monthlyProfit
             FROM tbl_transaction t
             JOIN transaction_items ti ON t.transaction_id = ti.transaction_id
             JOIN item i ON ti.item_code = i.item_code
             WHERE YEAR(t.date_time) = YEAR(CURDATE())
               AND MONTH(t.date_time) = MONTH(CURDATE())
             GROUP BY YEAR(t.date_time), MONTH(t.date_time);
             """;
        try(Connection conn = DatabaseConnection.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)){
        
        while (rs.next()){
          
            int month =  rs.getInt("transaction_month");
            double profit = rs.getDouble("monthlyProfit");
            System.out.println("Month is "+month);
            System.out.println("Profit is "+profit);
            
            return new ReportModel(month, profit);
            
        }
    }
    catch(SQLException e){
        e.printStackTrace();
    }
        return null;
}
    
    public ReportModel getWeeklyProfit(){
        String sql = """
             SELECT 
                    CONCAT(
                        DATE_FORMAT(DATE_SUB(t.date_time, INTERVAL WEEKDAY(t.date_time) DAY), '%Y-%m-%d'),
                        ' to ',
                        DATE_FORMAT(DATE_ADD(t.date_time, INTERVAL (6 - WEEKDAY(t.date_time)) DAY), '%Y-%m-%d')
                    ) AS transaction_week,
                    SUM((ti.sellingPrice - i.cost_price) * ti.quantity) AS weeklyProfit
             FROM tbl_transaction t
             JOIN transaction_items ti ON t.transaction_id = ti.transaction_id
             JOIN item i ON ti.item_code = i.item_code
             WHERE YEARWEEK(t.date_time, 1) = YEARWEEK(CURDATE(), 1)
             GROUP BY YEARWEEK(t.date_time, 1);
             """;
        
        try(Connection conn = DatabaseConnection.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)){
            while (rs.next())   {
                String week = rs.getString("transaction_week");
                double profit = rs.getDouble("weeklyProfit");
                
                System.out.println("Week is "+week);
                  System.out.println("Profit is "+profit);
                
                return new ReportModel(week, profit);
            }         
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public ReportModel getYearProfit(){
        String sql = """
                     SELECT YEAR(t.date_time) AS transaction_year,
                     SUM(t.total_amount) AS yearlySales,
                     SUM((ti.sellingPrice - i.cost_price) * ti.quantity) AS yearlyProfit
                     FROM tbl_transaction t
                     JOIN transaction_items ti ON t.transaction_id = ti.transaction_id
                     JOIN item i ON ti.item_code = i.item_code
                     WHERE YEAR(t.date_time) = YEAR(CURDATE())
                     GROUP BY YEAR(t.date_time);
                     """;
        
        try(Connection conn = DatabaseConnection.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql)){
            
            while (rs.next()){
                int year = rs.getInt("transaction_year");
                double yearlySales = rs.getDouble("yearlySales");
                double yearlyProfit = rs.getDouble("yearlyProfit");
                
                return new ReportModel(year, yearlySales, yearlyProfit);
            }
        }
        catch(SQLException e){ 
            e.printStackTrace();
        }
        return null;
        
    }
}
