package uk.co.primaltech.stockmanagement.database;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * HyperSQL supports PRIMARY KEY, UNIQUE and FOREIGN KEY constraints, which can
 * span multiple columns.
 */
/**
 *
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 */
public class GenerateTables {

    public enum Table {

        PRODUCTS("Product"),
        USERS("Users");

        private final String value;

        Table(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public GenerateTables() {
        initConfig();
    }

    private void initConfig() {
        try (Statement stm = DBConnection.getInstance().getConnection().createStatement()) {
            //set all tables to be created using MEMORY 
            //database will be hold in memory and when close writen to disk.
            
            stm.execute("SET DATABASE DEFAULT TABLE TYPE MEMORY");            

        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }        
    }

    public boolean createProduct() {
        try (Statement stm = DBConnection.getInstance().getConnection().createStatement()) {
            stm.executeUpdate("CREATE TABLE " + Table.PRODUCTS.value + "("
                    + "IDProduct INTEGER IDENTITY PRIMARY KEY, "
                    + "ProductName VARCHAR(256), "
                    + "Brand VARCHAR(256), "
                    + "Serial VARCHAR(256), "
                    + "Supplier VARCHAR(256), "
                    + "DateIN Date, "
                    + "DateOUT Date, "
                    + "Price VARCHAR(256),"
                    + "Invoice VARCHAR(256),"
                    + "UNIQUE(ProductName, Serial, Brand)"
                    + ")");
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
            return false;
        }    
        System.out.println("Table \"" + Table.PRODUCTS.toString() + "\" created!");
        return true;
    }

    public boolean createProductClientRelation() {
        try (Statement stm = DBConnection.getInstance().getConnection().createStatement()) {
            stm.executeUpdate("CREATE TABLE " + Table.PRODUCTS.getValue().concat("_").concat(Table.USERS.getValue()) + "("
                    + "RefIDProduct INTEGER FOREIGN KEY REFERENCES " + Table.PRODUCTS.getValue() + "(IDProduct),"
                    + "RefIDClient INTEGER FOREIGN KEY REFERENCES " + Table.USERS.getValue() + "(IDUser),"
                    + "CONSTRAINT PK_ProductUser PRIMARY KEY(RefIDProduct, RefIDClient)"
                    + ")");
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
            return false;
        }
        System.out.println("Table \"" + Table.PRODUCTS.toString().concat("_").concat(Table.USERS.toString())+ "\" created!");
        return true;
    }

    public boolean createUsers() {
        try (Statement stm = DBConnection.getInstance().getConnection().createStatement()) {
            stm.executeUpdate("CREATE TABLE " + Table.USERS.value + "("
                    + "IDUser INTEGER IDENTITY PRIMARY KEY, "
                    + "Name VARCHAR(256), "
                    + "City VARCHAR(256), "
                    + "State VARCHAR(256), "
                    + "Street VARCHAR(256), "
                    + "ZipCode VARCHAR(256), "
                    + "DoorNumber VARCHAR(256), "
                    + "Country VARCHAR(256),"
                    + "Phone VARCHAR(256),"
                    + "CompanyName VARCHAR(256)"
                    + ")");
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
            return false;
        }
        System.out.println("Table \"" + Table.USERS.toString() + "\" created!");
        return true;
    }
}