package uk.co.primaltech.stockmanagement.product;

import java.util.Date;

/**
 *
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 */
public class Product {
    
    private String productName;
    private String brand;
    private String serial;
    private String supplier;
    private Date dateIN;
    private Date dateOUT;
    private String price;
    private String invoice;
    private int dbID;

    public Product(String productName, String brand, String serial, String supplier, Date dateIN, Date dateOUT, String boughtPriece, String invoice) {
        this.productName = productName;
        this.brand = brand;
        this.serial = serial;
        this.supplier = supplier;
        this.dateIN = dateIN;
        this.dateOUT = dateOUT;
        this.price = boughtPriece;
        this.invoice = invoice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getString() {
        return brand;
    }

    public void setString(String brand) {
        this.brand = brand;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Date getDateIN() {
        return dateIN;
    }

    public void setDateIN(Date dateIN) {
        this.dateIN = dateIN;
    }

    public Date getDateOUT() {
        return dateOUT;
    }

    public void setDateOUT(Date dateOUT) {
        this.dateOUT = dateOUT;
    }

    public String getPrice() {
        return price;
    }

    public void setBoughtPriece(String boughtPriece) {
        this.price = boughtPriece;
    }

    public String getBrand() {
        return brand;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public int getDbID() {
        return dbID;
    }

    public void setDbID(int dbID) {
        this.dbID = dbID;
    }
}
