package uk.co.primaltech.stockmanagement.database;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import uk.co.primaltech.stockmanagement.database.GenerateTables.Table;
import uk.co.primaltech.stockmanagement.product.Product;

/**
 *
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 */
public class DBInsert {

    public static int newProduct(Product p) {
        assert p != null;
        int id = -1;

        String data = "INSERT INTO " + Table.PRODUCTS.getValue()
                + "(ProductName, Brand, Serial, Supplier, DateIN, DateOUT, Price, Invoice) VALUES"
                + "(?,?,?,?,?,?,?,?)";

        try {
            try (PreparedStatement ps = DBConnection.getInstance().getConnection().prepareStatement(data, Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, p.getProductName());
                ps.setString(2, p.getBrand());
                ps.setString(3, p.getSerial());
                ps.setString(4, p.getSupplier());
                ps.setDate(5, new java.sql.Date(p.getDateIN().getTime()));
                if (p.getDateOUT() == null) {
                    ps.setDate(6, null);
                } else {
                    ps.setDate(6, new java.sql.Date(p.getDateOUT().getTime()));
                }
                ps.setString(7, p.getPrice());
                ps.setString(8, p.getInvoice());

                // execute insert SQL statement
                ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                }                
                return id;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
            return id;
        }
    }
}
