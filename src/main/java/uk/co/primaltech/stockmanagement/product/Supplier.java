package uk.co.primaltech.stockmanagement.product;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import uk.co.primaltech.stockmanagement.ApplicationSettings;

/**
 *
 * @author Nuno
 */
public class Supplier {

    public enum SupplierType {
        VIP,
        PDS,
        SPIRE,
        ENTA,
        MICRO_PI
    }

    private static volatile Supplier instance;
    private final List<String> supplierList;

    public static Supplier getInstance() {
        if (instance == null) {
            synchronized (Supplier.class) {
                if (instance == null) {
                    instance = new Supplier();
                }
            }
        }
        return instance;
    }

    private Supplier() {
        supplierList = new ArrayList<>(SupplierType.values().length);
        for (SupplierType type : SupplierType.values()) {
            supplierList.add(type.toString().toUpperCase());
        }
    }

    public List<String> getSupplierList() {
        return supplierList;    
    }
    
    public String getSupplier(String supplier){
        assert supplier != null && !supplier.isEmpty();
        for(String s : supplierList){
            if (s.equalsIgnoreCase(supplier))
                return s;
        }
        return null;
    }

    public void addSupplier(String supplier) {
        if (supplier != null && !supplierList.contains(supplier.toUpperCase()) && !supplier.isEmpty()) {
            supplierList.add(supplier.toUpperCase());
            File supplierFile = new File(new File((String) ApplicationSettings.getInstance().getProperty("home_dir", String.class)), (String) ApplicationSettings.getInstance().getProperty("suppliers", String.class));
            try {
                FileWriter writer = new FileWriter(supplierFile, true);
                try (BufferedWriter bw = new BufferedWriter(writer)) {                    
                    bw.write(supplier.trim().toUpperCase());
                    bw.newLine();
                }
            } catch (IOException ex) {
                System.out.println(ex.getLocalizedMessage());
            }
        }
    }
}