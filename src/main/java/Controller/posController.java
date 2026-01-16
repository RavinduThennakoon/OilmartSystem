/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Item;
import Model.PosDAO;
import Model.Transaction;
import Model.TransactionItem;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author ravin
 */
public class posController {
       public static void insertFullTransaction(String dateStr, String cashierIdStr, String totalAmountStr, String discountStr, DefaultTableModel model) {
    // Validate
    if (dateStr.isEmpty() || cashierIdStr.isEmpty() || totalAmountStr.isEmpty() || discountStr.isEmpty()) {
        JOptionPane.showMessageDialog(null, " Insufficient values ", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        // Parse values
       
        double discount = Double.parseDouble(discountStr);
        double totalAmount = Double.parseDouble(totalAmountStr);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date utilDate = sdf.parse(dateStr);

        // Insert transaction and get PK
        Transaction transaction = new Transaction(utilDate, cashierIdStr, totalAmount, discount);
        PosDAO dao = new PosDAO();
        int transactionId = dao.addTransaction(transaction);

        if (transactionId == -1) {
            JOptionPane.showMessageDialog(null, "Failed to insert transaction", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Loop table and insert items
        for (int i = 0; i < model.getRowCount(); i++) {
            int itemCode = Integer.parseInt(model.getValueAt(i, 0).toString());
            String itemName = model.getValueAt(i, 1).toString();
            String category = model.getValueAt(i, 2).toString();
            String vehicleType = model.getValueAt(i, 3).toString();
             int quantity = Integer.parseInt(model.getValueAt(i, 5).toString());
            double sellingPrice = Double.parseDouble(model.getValueAt(i, 4).toString());
            double total = Double.parseDouble(model.getValueAt(i, 6).toString());
            double itemDiscount = Double.parseDouble(model.getValueAt(i, 7).toString());
            

            TransactionItem item = new TransactionItem(itemCode, itemName, category, vehicleType,quantity, sellingPrice,  total, itemDiscount);
            dao.addTransactionItem(item, transactionId);
        }

        JOptionPane.showMessageDialog(null, "Transaction saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}

    
}
