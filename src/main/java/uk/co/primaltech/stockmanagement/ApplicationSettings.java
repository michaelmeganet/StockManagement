package uk.co.primaltech.stockmanagement;

import uk.co.primaltech.stockmanagement.product.Brand;
import uk.co.primaltech.stockmanagement.product.Supplier;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Class to manage application settings
 *
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 *
 */
public class ApplicationSettings {
    /* Settings list: map the name of the setting to an object (with a class). */

    private static Map<String, String> settings;

    /* Fixed application settings. */
    private static final String home_dir = System.getProperty("user.home");
    private static final String separator = System.getProperty("file.separator");
    private static final String primal_home_dir = home_dir + separator + "Google Drive" + separator + "Software" + separator + Main.getName() + separator;
    private static final String settingsFileName = "settings.ini";
    private static final String brandsFileName = "brands.txt";
    private static final String suppliersFileName = "suppliers.txt";
    private static final String databaseFileName = "primal.db";

    private static Properties properties;
    private static volatile ApplicationSettings instance = null;

    public static ApplicationSettings getInstance() {
        if (instance == null) {
            synchronized (ApplicationSettings.class) {
                if (instance == null) {
                    instance = new ApplicationSettings();
                }
            }
        }
        return instance;
    }

    private ApplicationSettings() {
        settings = new HashMap<>();

        /* Load all settings from file. */
        try {
            properties = new Properties();
            File propertiesFile = new File(primal_home_dir, settingsFileName);
            FileInputStream fis = new FileInputStream(propertiesFile);
            properties.load(fis);

            System.out.println("Loading settings file...");
            if ((properties.getProperty("primalVersion") == null)
                    || !properties.getProperty("primalVersion").equals(Main.getVersion())) {
                System.out.println("Found an outdated version of Primal Tech Software. Updating settings to newer version...");
                fis.close();
                propertiesFile.delete();
                createDefaultSettingsFile();
                createDefaultBrandFile();
                createDefaultSupplierFile();
                return;
            }

            System.out.println("Loading brands file...");
            createDefaultBrandFile();

            System.out.println("Loading suppliers file...");
            createDefaultSupplierFile();

            for (Object key : properties.keySet()) {
                settings.put((String) key, properties.getProperty((String) key));
            }

        } catch (IOException e) {
            if (!new File(primal_home_dir, settingsFileName).exists()) {
                createDefaultSettingsFile();
                createDefaultBrandFile();
                createDefaultSupplierFile();
            }
        }
    }

    private static void createDefaultBrandFile() {
        System.out.println("Creating new brand file.... if needed." + brandsFileName);
        File brandFile = new File(new File(primal_home_dir), brandsFileName);
        if (!brandFile.exists()) {
            try (PrintStream writer = new PrintStream(brandFile)) {
                for (String brand : Brand.getInstance().getBrandList()) {
                    writer.println(brand);
                }
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getLocalizedMessage());
            }

        }
        System.out.println("Brand file sucessfull created/loaded.");
    }

    private static void createDefaultSupplierFile() {
        System.out.println("Creating new supplier file.... if needed" + suppliersFileName);
        File supplierFile = new File(new File(primal_home_dir), suppliersFileName);
        if (!supplierFile.exists()) {
            try (PrintStream writer = new PrintStream(supplierFile)) {
                for (String supplier : Supplier.getInstance().getSupplierList()) {
                    writer.println(supplier);
                }
            } catch (FileNotFoundException ex) {
                System.out.println(ex.getLocalizedMessage());
            }
        }
        System.out.println("Suppliers file sucessfull created/loaded. ");
    }

    private static void createDefaultSettingsFile() {
        System.out.println("Creating new settings file.... " + settingsFileName);

        System.out.println(primal_home_dir);
        File createDir = new File(primal_home_dir);
        if (createDir.exists()) {
            createDir.delete();
        }
        createDir.mkdirs();
        File settingsFile = new File(createDir.getParent(), settingsFileName);

        if (settingsFile.exists()) {
            settingsFile.delete();
        }
        /* Folders and files. */
        setProperty("primalVersion", Main.getVersion(), String.class);
        setProperty("home_dir", primal_home_dir, String.class);
        setProperty("primalEmail", "support@primaltech.co.uk", String.class);
        setProperty("database", databaseFileName, String.class);
        setProperty("brands", brandsFileName, String.class);
        setProperty("suppliers", suppliersFileName, String.class);

        System.out.println("Settings file sucessfull created.");
    }

    public static void setProperty(String propertieName, Object value, Class type) {
        //assert getInstance().settings.containsKey(propertieName);

        String strValue = null;

        if (type == String.class) {
            strValue = (String) value;
        }

        if (type == Integer.class) {
            strValue = Integer.toString((Integer) value);
        }

        if (type == Boolean.class) {
            strValue = Boolean.toString((Boolean) value);
        }

        if (type == Double.class) {
            strValue = Double.toString((Double) value);
        }

        if (type == Color.class) {
            strValue = Integer.toString(((Color) value).getRGB());
        }

        settings.put(propertieName, strValue);
        properties.put(propertieName, strValue);

        try {
            try (FileOutputStream out = new FileOutputStream(new File(primal_home_dir, settingsFileName))) {
                properties.store(out, "/* properties updated on: */");
                out.flush();
            }
        } catch (IOException ex) {
        }
    }

    public Object getProperty(String propertieName, Class returnType) {
        assert ApplicationSettings.settings.containsKey(propertieName);

        if (returnType == String.class) {
            return String.valueOf(settings.get(propertieName));
        }

        if (returnType == Integer.class) {
            return Integer.valueOf(settings.get(propertieName));
        }

        if (returnType == Boolean.class) {
            return Boolean.parseBoolean(settings.get(propertieName));
        }

        if (returnType == Double.class) {
            return Double.parseDouble(settings.get(propertieName));
        }

        if (returnType == Color.class) {
            return Color.decode(settings.get(propertieName));
        }
        return null;
    }
}