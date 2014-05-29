package uk.co.primaltech.stockmanagement.GUI.product;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JComboBox;
import uk.co.primaltech.stockmanagement.ApplicationSettings;
import uk.co.primaltech.stockmanagement.product.Product;

/**
 *
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 */
public class ProductEntryController {

    private static volatile ProductEntryController instance = null;

    public static ProductEntryController getInstance() {
        if (instance == null) {
            synchronized (ProductEntryController.class) {
                if (instance == null) {
                    instance = new ProductEntryController();
                }
            }
        }
        return instance;
    }

    public ProductFieldErrorControl checkProduct(Product product) {
        assert product != null;

        if (product.getProductName() == null || product.getProductName().isEmpty()) {
            return ProductFieldErrorControl.ERROR_PRODUCT_NAME;
        }

        if (product.getBrand() == null || product.getBrand().isEmpty()) {
            return ProductFieldErrorControl.ERROR_BRAND;
        }

        if (product.getSerial() == null || product.getSerial().isEmpty()) {
            return ProductFieldErrorControl.ERROR_SERIAL;
        }

        if (product.getSupplier() == null || product.getSupplier().isEmpty()) {
            return ProductFieldErrorControl.ERROR_SUPPLIER;
        }

        if (product.getDateIN() == null) {
            return ProductFieldErrorControl.ERROR_DATE_IN;
        } else {
            //check format
        }

        if (product.getDateOUT() != null) {
            //check format
        }

        if (product.getPrice() == null || product.getPrice().isEmpty()) {
            return ProductFieldErrorControl.ERROR_PRICE;
        }
        try {
            String aux = product.getPrice().replaceAll("\\s+", "");
            Double.parseDouble(aux);
        } catch (NumberFormatException ex) {
            return ProductFieldErrorControl.ERROR_PRICE;
        }
        return ProductFieldErrorControl.SUCCESS;
    }

    public void addDefaultBrands() {
        BufferedReader br = null;
        try {
            File file = new File(new File((String) ApplicationSettings.getInstance().getProperty("home_dir", String.class
            )), (String) ApplicationSettings.getInstance().getProperty("brands", String.class));
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                if (!containItem(ProductEntryGUI.getInstance().getjComboBrand(), line.toUpperCase())) {
                    line = line.replaceAll(" ", "");
                    ProductEntryGUI.getInstance().getjComboBrand().addItem(line.toUpperCase());
                }

            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
            }
        }
    }

    public void addDefaultSuppliers() {
        BufferedReader br = null;

        try {
            File file = new File(new File((String) ApplicationSettings.getInstance().getProperty("home_dir", String.class
            )), (String) ApplicationSettings.getInstance().getProperty("suppliers", String.class
            ));
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null && !line.isEmpty()) {
                if (!containItem(ProductEntryGUI.getInstance().getjComboSupplier(), line.toUpperCase())) {
                    line = line.replaceAll(" ", "");
                    ProductEntryGUI.getInstance().getjComboSupplier().addItem(line.toUpperCase());
                }
            }
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
            }
        }
    }

    private boolean containItem(JComboBox cb, Object item) {
        for (int index = 0; index < cb.getItemCount(); index++) {
            if (item.equals(cb.getItemAt(index))) {
                return true;
            }
        }
        return false;
    }
}
