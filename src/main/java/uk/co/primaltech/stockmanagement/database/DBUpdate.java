package uk.co.primaltech.stockmanagement.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import uk.co.primaltech.stockmanagement.database.GenerateTables.Table;
import uk.co.primaltech.stockmanagement.product.Product;

/**
 *
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 */
public class DBUpdate {

    public static boolean updateProduct(Product product) {
        assert product != null;

        try {
            String updateProduct = "UPDATE " + Table.PRODUCTS.getValue() + " SET "
                    + "ProductName = ?, "
                    + "Brand = ?, "
                    + "Serial = ?, "
                    + "Supplier = ?, "
                    + "DateIN = ?, "
                    + "DateOUT = ?, "
                    + "Price = ? WHERE IDProduct = ?";
            
            try (PreparedStatement stm = DBConnection.getInstance().getConnection().prepareStatement(updateProduct)) {
                stm.setString(1, product.getProductName()); // product name
                stm.setString(2, product.getBrand()); // brand
                stm.setString(3, product.getSerial()); // serial
                stm.setString(4, product.getSupplier()); // supplier
                stm.setDate(5, new java.sql.Date(product.getDateIN().getTime())); // date in
                if (product.getDateOUT() != null){
                    stm.setDate(6, new java.sql.Date(product.getDateOUT().getTime())); // date out
                }else{
                    stm.setDate(6, null); // date out
                }
                stm.setString(7, product.getPrice()); // price
                stm.setInt(8, product.getDbID()); // price
                
                stm.executeUpdate();
            } // product name
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
            return false;
        }
    }

}
