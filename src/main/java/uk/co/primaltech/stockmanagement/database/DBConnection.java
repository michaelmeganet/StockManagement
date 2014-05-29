package uk.co.primaltech.stockmanagement.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

import uk.co.primaltech.stockmanagement.ApplicationSettings;

/**
 *
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 */
public class DBConnection {

    private static volatile DBConnection instance = null;
    private Connection conn;

    private final static String databaseName = (String) ApplicationSettings.getInstance().getProperty("database", String.class);
    private final static String dbPath = (String) ApplicationSettings.getInstance().getProperty("home_dir", String.class);
    private final static String DB_USER = "sa";
    private final static String DB_PASSWORD = "";

    public static DBConnection getInstance() {
        if (instance == null) {
            synchronized (DBConnection.class) {
                if (instance == null) {
                    instance = new DBConnection();
                    createDBTables();
                }
            }
        }
        return instance;
    }
    
    private static void createDBTables(){
        GenerateTables gt = new GenerateTables();
        try {
            DatabaseMetaData meta = instance.getConnection().getMetaData();
            
            System.out.println("Checking if database exists and is correct...");
            
            ResultSet res = meta.getTables(null, null,"PRODUCT" , null);
            if (!res.next())
                if(!gt.createProduct()){
                    JOptionPane.showMessageDialog(null, "Could launch or create database...\nPlease contact nuno.mogas@gmail.com");
                    instance.shutdown();
                    System.exit(0);
                }
            
            res = meta.getTables(null, null,"USERS" , null); 
            if (!res.next())
                if(!gt.createUsers()){
                    JOptionPane.showMessageDialog(null, "Could launch or create database...\nPlease contact nuno.mogas@gmail.com");
                    instance.shutdown();
                    System.exit(0);
                }
            
            res = meta.getTables(null, null,"PRODUCT_USERS" , null); 
            if (!res.next())
                if(!gt.createProductClientRelation()){
                    JOptionPane.showMessageDialog(null, "Could launch or create database...\nPlease contact nuno.mogas@gmail.com");
                    instance.shutdown();
                    System.exit(0);
                }
            
        } catch (SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
        System.out.println("Database pre-operations: ALL DONE");                
    }

    /* private constructor to avoid instantiation 
     * connnection to the db - presist for life of program
     */
    private DBConnection() {
        try {
            // Load the HSQL Database Engine JDBC driver
            Class.forName("org.hsqldb.jdbcDriver");

            // connect to the database.   This will load the db files and start the
            // database if it is not already running.
            conn = DriverManager.getConnection("jdbc:hsqldb:"
                    + dbPath.concat(databaseName), //database path
                    DB_USER, //username
                    DB_PASSWORD);    //password
            /* create tables if they do not exist*/                    

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getLocalizedMessage());
        }
    }

    public void shutdown() throws SQLException {

        Statement st = conn.createStatement();

        // db writes out to files and performs clean shuts down
        // otherwise there will be an unclean shutdown when program ends
        st.execute("SHUTDOWN");
        conn.close();    // if there are no other open connection
    }

    public Connection getConnection() {
        return conn;
    }
}
