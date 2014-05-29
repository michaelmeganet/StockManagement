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
public class Brand {

    public enum BrandType {
        CORSAIR,
        OCZ,
        NVIDIA,
        INTEL,
        ASUS,
        EVGA,
        LOGITECH,
        ANTEC,
        MSI
    }

    private final List<String> brandList;

    private static volatile Brand instance;

    public static Brand getInstance() {
        if (instance == null) {
            synchronized (Brand.class) {
                if (instance == null) {
                    instance = new Brand();
                }
            }
        }
        return instance;
    }

    private Brand() {
        brandList = new ArrayList<>(BrandType.values().length);
        for (BrandType type : BrandType.values()) {
            brandList.add(type.toString().toUpperCase());
        }
    }
    
    public String getBrand(String brand){
        assert brand != null && !brand.isEmpty();
        for(String s : brandList){
            if (s.equalsIgnoreCase(brand))
                return s;
        }
        return null;
    }

    public List<String> getBrandList() {
        return brandList;
    }

    public void addBrand(String brand) {
        if (brand != null && !brandList.contains(brand.toUpperCase()) && !brand.isEmpty()) {
            brandList.add(brand.toUpperCase());
            File brandFile = new File(new File((String) ApplicationSettings.getInstance().getProperty("home_dir", String.class)), (String) ApplicationSettings.getInstance().getProperty("brands", String.class));
            try {                
                FileWriter writer = new FileWriter(brandFile, true);
                try (BufferedWriter bw = new BufferedWriter(writer)) {                    
                    bw.write(brand.trim().toUpperCase());
                    bw.newLine();
                }
            } catch (IOException ex) {
                System.out.println(ex.getLocalizedMessage());
            }
        }
    }
}