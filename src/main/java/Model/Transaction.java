/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.util.Date;

/**
 *
 * @author ravin
 */
public class Transaction {
       private String cashierid;
      private double totalAmount,discount;
     private Date date;
    
     
     
     
     public Transaction(Date date, String cashierid, double totalAmount, double discount){
        
               this.date = date;
               this.cashierid = cashierid;
               this.totalAmount = totalAmount;
               this.discount = discount;
     }
     
    
     
     public Date getDate() {
        return date;
    }
    public String getCashierId(){
        return cashierid;
    }
    public double getTotalAmount(){
        return totalAmount;
    } 
    public double getDiscount(){
        return discount;
    }
       
   
    public void setDate(Date date) {
        this.date = date;
    } 
     public void setCashierId(String cashierid){
        this.cashierid = cashierid;
    }
    public void setTotalAmount(double totalAmount)
    {
        this.totalAmount = totalAmount;
    }
    public void setDiscount(double discount){
        this.discount = discount;
    }
}

