package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Statement;


public class ItemDAO {

    public void addItem(Item item) {
        String sql = "INSERT INTO item " +
                "(item_name, brand, category, vehicle_type, viscosity_grade, api_standard, " +
                "volumn, unit, package_type, batch_no, expiry_date, cost_price, selling_price, " +
                "discount, stock_qty, supplier) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, item.getItemName());
            pstmt.setString(2, item.getBrand());
            pstmt.setString(3, item.getCategory());
            pstmt.setString(4, item.getVehicleType());
            pstmt.setString(5, item.getViscosityGrade());
            pstmt.setString(6, item.getApiStandard());
            pstmt.setDouble(7, item.getVolumn());       // changed to double
            pstmt.setString(8, item.getUnit());
            pstmt.setString(9, item.getPackageType());
            pstmt.setString(10, item.getBatchNo());
            pstmt.setDate(11, item.getExpiryDate());
            pstmt.setDouble(12, item.getCostPrice());   // changed to double
            pstmt.setDouble(13, item.getSellingPrice());// changed to double
            pstmt.setDouble(14, item.getDiscount());    // changed to double
            pstmt.setInt(15, item.getStockQty());
            pstmt.setString(16, item.getSupplier());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Item> getAllItems() {
        List<Item> itemList = new ArrayList<>();
        String sql = "SELECT * FROM item";

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Item item = new Item(
                     rs.getInt("item_code"),
                    rs.getString("item_name"),
                    rs.getString("brand"),
                    rs.getString("category"),
                    rs.getString("vehicle_type"),
                    rs.getString("viscosity_grade"),
                    rs.getString("api_standard"),
                    rs.getDouble("volumn"),
                    rs.getString("unit"),
                    rs.getString("package_type"),
                    rs.getString("batch_no"),
                    rs.getDate("expiry_date"),
                    rs.getDouble("cost_price"),
                    rs.getDouble("selling_price"),
                    rs.getDouble("discount"),
                    rs.getInt("stock_qty"),
                    rs.getString("supplier")
                );
                itemList.add(item);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itemList;
    }
    public void updateItem(Item item) {
    String sql = "UPDATE item SET item_name=?, brand=?, category=?, vehicle_type=?, viscosity_grade=?, " +
                 "api_standard=?, volumn=?, unit=?, package_type=?, batch_no=?, expiry_date=?, cost_price=?, " +
                 "selling_price=?, discount=?, stock_qty=?, supplier=? WHERE item_code=?";

    try (Connection conn = DatabaseConnection.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, item.getItemName());
        pstmt.setString(2, item.getBrand());
        pstmt.setString(3, item.getCategory());
        pstmt.setString(4, item.getVehicleType());
        pstmt.setString(5, item.getViscosityGrade());
        pstmt.setString(6, item.getApiStandard());
        pstmt.setDouble(7, item.getVolumn());
        pstmt.setString(8, item.getUnit());
        pstmt.setString(9, item.getPackageType());
        pstmt.setString(10, item.getBatchNo());
        pstmt.setDate(11, item.getExpiryDate());
        pstmt.setDouble(12, item.getCostPrice());
        pstmt.setDouble(13, item.getSellingPrice());
        pstmt.setDouble(14, item.getDiscount());
        pstmt.setInt(15, item.getStockQty());
        pstmt.setString(16, item.getSupplier());
        pstmt.setInt(17, item.getItemCode()); // WHERE item_code=?

        pstmt.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    public void deleteItem(int itemCode) {
    String sql = "DELETE FROM item WHERE item_code=?";

    try (Connection conn = DatabaseConnection.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, itemCode);
        pstmt.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    
    
    public boolean exists(int itemCode) {
    String sql = "SELECT 1 FROM item WHERE item_code = ?";
    try (Connection conn = DatabaseConnection.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, itemCode);
        ResultSet rs = pstmt.executeQuery();
        return rs.next(); // true if found in DB
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}


    
    public int getNextItemCode() {
    int nextId = 1; // default if table is empty
    String sql = "SELECT AUTO_INCREMENT FROM information_schema.TABLES " +
                 "WHERE TABLE_SCHEMA = 'oilmart' AND TABLE_NAME = 'item'";

    try (Connection conn = DatabaseConnection.connect();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        if (rs.next()) {
            nextId = rs.getInt("AUTO_INCREMENT");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return nextId;
}
    public List<String> searchItemNames(String keyword) {
    List<String> names = new ArrayList<>();
    String sql = "SELECT item_name FROM item WHERE item_name LIKE ? LIMIT 10"; 

    try (Connection conn = DatabaseConnection.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, "%" + keyword + "%");
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            names.add(rs.getString("item_name"));
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return names;
}
    public List<Item> searchItems(String itemName, String brand, String category) {
    List<Item> itemList = new ArrayList<>();
    
    // Base SQL with WHERE 1=1 to simplify appending conditions
    String sql = "SELECT * FROM item WHERE 1=1";

    // Build conditions dynamically
    if (itemName != null && !itemName.isEmpty()) {
        sql += " AND item_name LIKE ?";
    }
    if (brand != null && !brand.isEmpty()) {
        sql += " AND brand LIKE ?";
    }
    if (category != null && !category.isEmpty()) {
        sql += " AND category LIKE ?";
    }

    try (Connection conn = DatabaseConnection.connect();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        int paramIndex = 1;

        if (itemName != null && !itemName.isEmpty()) {
            pstmt.setString(paramIndex++, "%" + itemName + "%");
        }
        if (brand != null && !brand.isEmpty()) {
            pstmt.setString(paramIndex++, "%" + brand + "%");
        }
        if (category != null && !category.isEmpty()) {
            pstmt.setString(paramIndex++, "%" + category + "%");
        }

        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Item item = new Item(
                rs.getInt("item_code"),
                rs.getString("item_name"),
                rs.getString("brand"),
                rs.getString("category"),
                rs.getString("vehicle_type"),
                rs.getString("viscosity_grade"),
                rs.getString("api_standard"),
                rs.getDouble("volumn"),
                rs.getString("unit"),
                rs.getString("package_type"),
                rs.getString("batch_no"),
                rs.getDate("expiry_date"),
                rs.getDouble("cost_price"),
                rs.getDouble("selling_price"),
                rs.getDouble("discount"),
                rs.getInt("stock_qty"),
                rs.getString("supplier")
            );
            itemList.add(item);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return itemList;
}
    public int getItemCount() {
    int count = 0;
    String sql = "SELECT COUNT(*) FROM item";

    try (Connection conn = DatabaseConnection.connect();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        if (rs.next()) {
            count = rs.getInt(1);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return count;
}
 public int[] getStockCounts() {
    int[] counts = new int[3]; // counts[0] = in stock, counts[1] = out of stock
    String sql = "SELECT " +
                 "SUM(CASE WHEN stock_qty > 0 THEN 1 ELSE 0 END) AS in_stock, " +
                 "SUM(CASE WHEN stock_qty < 10 THEN 1 ELSE 0 END) AS low_stock, " +
                 "SUM(CASE WHEN stock_qty = 0 THEN 1 ELSE 0 END) AS out_of_stock " +
                 "FROM item";

    try (Connection conn = DatabaseConnection.connect();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        if (rs.next()) {
            counts[0] = rs.getInt("in_stock");
            counts[1] = rs.getInt("out_of_stock");
            counts[2] = rs.getInt("low_stock");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return counts;
}
   
}
