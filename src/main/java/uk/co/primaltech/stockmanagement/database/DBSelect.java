package uk.co.primaltech.stockmanagement.database;

import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.co.primaltech.stockmanagement.database.GenerateTables.Table;
import uk.co.primaltech.stockmanagement.product.Product;

/**
 *
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 */
public class DBSelect {

    public static List<String> getColumnNames(Table table) {
        
        try {
            assert table != null;
        
            DatabaseMetaData meta = DBConnection.getInstance().getConnection().getMetaData();
            
            ResultSet rs = meta.getColumns(null, null, table.getValue().toUpperCase(), null);        
            List<String> columnNames = new ArrayList<>();
            while(rs.next()){
                columnNames.add(rs.getString("COLUMN_NAME"));                
            }
            
            //remove id
            columnNames.remove(0);
            return columnNames;
                        
        } catch (SQLException ex) {
            Logger.getLogger(DBSelect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static List<Product> getProductByName(String name){
        assert name != null && !name.isEmpty();
        name = name.toUpperCase();
        List<Product> pList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        
        String[] words = name.split("\\s+");
        StringBuilder searchString = new StringBuilder();
        
        for(String word : words){
            searchString.append("upper(ProductName) LIKE ? OR ");
        }
        searchString.replace(searchString.length() - 4, searchString.length() - 1, "");        
        
        String productQuery = "SELECT IDProduct, ProductName, Brand, Serial, Supplier, DateIN, DateOUT, Price, Invoice " 
                        + "FROM " + Table.PRODUCTS.getValue() + " " 
                        + "WHERE " + searchString.toString() + "";
        
        try {
            preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(productQuery);                        
            int index = 1;
            for(String s : words){
                preparedStatement.setString(index, "%" + s + "%");
                index++;
            }
            
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Product p = new Product(rs.getString("ProductName"), 
                                        rs.getString("Brand"),
                                        rs.getString("Serial"),
                                        rs.getString("Supplier"),                        
                                        rs.getDate("DateIN"),
                                        rs.getDate("DateOUT"),
                                        rs.getString("Price"),
                                        rs.getString("Invoice"));
                p.setDbID(rs.getInt("IDProduct"));
                pList.add(p);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
            return pList;
        }
        
        return pList;
    }
}