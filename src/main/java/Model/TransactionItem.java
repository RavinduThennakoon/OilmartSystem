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
public class TransactionItem {
 
     
       private int itemCode,qty;
      private String itemName,category,vehicleType;
      private double sellingPrice,discount,total;
   
      
      
      public TransactionItem(int itemCode, String itemName, String categoryStr, String vehicleTypeStr, int qty, double sellingPrice, double total,   double discount)
      {
          this.itemCode = itemCode;
          this.itemName = itemName;
          this.category = categoryStr;
          this.vehicleType = vehicleTypeStr;
          this.qty = qty;
          this.sellingPrice= sellingPrice;
          this.total= total; 
          this.discount= discount;
      }
      
         public String getCategory() {
        return category;
    }
            public String getVehicleType() {
        return vehicleType;
    }
               public double gettotal() {
        return total;
    }
     
        public int getItemCode() {
        return itemCode;
    }

    public int getQty() {
        return qty;
    }

    public String getItemName() {
        return itemName;
    }


//    public double getVolume() {
//        return volume;
//    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public double getDiscount() {
        return discount;
    }
  
    // --- Setters ---
    public void setItemCode(int itemCode) {
        this.itemCode = itemCode;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
             public void setCategory() {
       this.category = category;
    }
            public void setVehicleType() {
        this.vehicleType = vehicleType;
    }
               public void settotal() {
        this.total = total;
    }
   
 

   
   
}
