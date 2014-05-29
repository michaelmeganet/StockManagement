package uk.co.primaltech.stockmanagement.database;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.co.primaltech.stockmanagement.database.GenerateTables.Table;

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

}
