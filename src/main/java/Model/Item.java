package Model;

import java.sql.Date;  // use java.sql.Date for DB storage

public class Item {
    private int item_code;
    private String item_name, brand, category,
            vehicle_type, viscosity_grade, api_standard,
            unit, package_type, batch_no, supplier;

    private double volumn, cost_price, selling_price, discount;
    private int stock_qty;
    private Date expiry_date;
    
       public Item(int item_code, String item_name, String brand, String category,
                String vehicle_type, String viscosity_grade, String api_standard,
                double volumn, String unit, String package_type, String batch_no,
                Date expiry_date, double cost_price, double selling_price,
                double discount, int stock_qty, String supplier) {
        this.item_code = item_code;
        this.item_name = item_name;
        this.brand = brand;
        this.category = category;
        this.vehicle_type = vehicle_type;
        this.viscosity_grade = viscosity_grade;
        this.api_standard = api_standard;
        this.volumn = volumn;
        this.unit = unit;
        this.package_type = package_type;
        this.batch_no = batch_no;
        this.expiry_date = expiry_date;
        this.cost_price = cost_price;
        this.selling_price = selling_price;
        this.discount = discount;
        this.stock_qty = stock_qty;
        this.supplier = supplier;
        
    }


    public Item(String item_name, String brand, String category,
                String vehicle_type, String viscosity_grade, String api_standard,
                double volumn, String unit, String package_type, String batch_no,
                Date expiry_date, double cost_price, double selling_price,
                double discount, int stock_qty, String supplier) {
       
        this.item_name = item_name;
        this.brand = brand;
        this.category = category;
        this.vehicle_type = vehicle_type;
        this.viscosity_grade = viscosity_grade;
        this.api_standard = api_standard;
        this.volumn = volumn;
        this.unit = unit;
        this.package_type = package_type;
        this.batch_no = batch_no;
        this.expiry_date = expiry_date;
        this.cost_price = cost_price;
        this.selling_price = selling_price;
        this.discount = discount;
        this.stock_qty = stock_qty;
        this.supplier = supplier;
       
    }

    // === Getters ===
    public int getItemCode() { return item_code; }
    public String getItemName() { return item_name; }
    public String getBrand() { return brand; }
    public String getCategory() { return category; }
    public String getVehicleType() { return vehicle_type; }
    public String getViscosityGrade() { return viscosity_grade; }
    public String getApiStandard() { return api_standard; }
    public double getVolumn() { return volumn; }
    public String getUnit() { return unit; }
    public String getPackageType() { return package_type; }
    public String getBatchNo() { return batch_no; }
    public Date getExpiryDate() { return expiry_date; }
    public double getCostPrice() { return cost_price; }
    public double getSellingPrice() { return selling_price; }
    public double getDiscount() { return discount; }
    public int getStockQty() { return stock_qty; }
    public String getSupplier() { return supplier; }
    

    // === Setters ===
    public void setItemName(String item_name) { this.item_name = item_name; }
    public void setBrand(String brand) { this.brand = brand; }
    public void setCategory(String category) { this.category = category; }
    public void setVehicleType(String vehicle_type) { this.vehicle_type = vehicle_type; }
    public void setViscosityGrade(String viscosity_grade) { this.viscosity_grade = viscosity_grade; }
    public void setApiStandard(String api_standard) { this.api_standard = api_standard; }
    public void setVolumn(double volumn) { this.volumn = volumn; }
    public void setUnit(String unit) { this.unit = unit; }
    public void setPackageType(String package_type) { this.package_type = package_type; }
    public void setBatchNo(String batch_no) { this.batch_no = batch_no; }
    public void setExpiryDate(Date expiry_date) { this.expiry_date = expiry_date; }
    public void setCostPrice(double cost_price) { this.cost_price = cost_price; }
    public void setSellingPrice(double selling_price) { this.selling_price = selling_price; }
    public void setDiscount(double discount) { this.discount = discount; }
    public void setStockQty(int stock_qty) { this.stock_qty = stock_qty; }
    public void setSupplier(String supplier) { this.supplier = supplier; }
   
}
