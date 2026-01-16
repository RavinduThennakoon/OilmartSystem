package Controller;

import Model.Item;
import Model.ItemDAO;
import java.util.List;
import javax.swing.*;
import java.sql.Date;

public class ItemController {

    public static void insertItem(String itemName, String brand, String category,
                                  String vehicleType, String viscosityGrade, String apiStandard,
                                  String volumnStr, String unit, String packageType, String batchNo,
                                  String expiryDateStr, String costPriceStr, String sellingPriceStr,
                                  String discountStr, String stockQtyStr, String supplier) {

        // === 1. Validate input ===
        if (itemName.isEmpty() || brand.isEmpty() || category.isEmpty() ||
                vehicleType.isEmpty() || viscosityGrade.isEmpty() || apiStandard.isEmpty() ||
                volumnStr.isEmpty() || unit.isEmpty() || packageType.isEmpty() || batchNo.isEmpty() ||
                expiryDateStr.isEmpty() || costPriceStr.isEmpty() || sellingPriceStr.isEmpty() ||
                discountStr.isEmpty() || stockQtyStr.isEmpty() || supplier.isEmpty()) {

            JOptionPane.showMessageDialog(null, " Insufficient values ", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // === 2. Convert to correct data types ===
            double volumn = Double.parseDouble(volumnStr);
            Date expiryDate = Date.valueOf(expiryDateStr.trim()); // yyyy-MM-dd
            double costPrice = Double.parseDouble(costPriceStr);
            double sellingPrice = Double.parseDouble(sellingPriceStr);
            double discount = Double.parseDouble(discountStr);
            int stockQty = Integer.parseInt(stockQtyStr);

            // === 3. Create Item Object ===
            Item item = new Item(itemName, brand, category, vehicleType,
                    viscosityGrade, apiStandard, volumn, unit, packageType,
                    batchNo, expiryDate, costPrice, sellingPrice,
                    discount, stockQty, supplier);

            // === 4. Call DAO ===
            new ItemDAO().addItem(item);

            // === 5. UI Feedback ===
            JOptionPane.showMessageDialog(null, "New Item has been inserted", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Invalid number format", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Invalid date format (use yyyy-MM-dd)", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void updateItem(Item item) {
        try {
            new ItemDAO().updateItem(item);
            JOptionPane.showMessageDialog(null, "Item updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to update item: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void deleteItem(int itemCode) {
    try {
        new ItemDAO().deleteItem(itemCode);
        JOptionPane.showMessageDialog(null, "Item deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Failed to delete item: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    public static boolean exists(int itemCode) {
    return new ItemDAO().exists(itemCode);
}

    
    public static List<Item> getAllItems() {
        return new ItemDAO().getAllItems();
    }
public static List<Item> searchItems(String itemName, String brand, String category) {
    return new ItemDAO().searchItems(itemName, brand, category);
}

    public static int getNextItemCode() {
    return new ItemDAO().getNextItemCode();
}
    public static int getItemCount() {
    return new ItemDAO().getItemCount();
}
 public static int[] getStockCounts() {
    return new ItemDAO().getStockCounts(); // returns [inStock, outOfStock]
}




}
 