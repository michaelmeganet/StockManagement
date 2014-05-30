package uk.co.primaltech.stockmanagement;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import uk.co.primaltech.stockmanagement.GUI.main.MainWindow;
import uk.co.primaltech.stockmanagement.GUI.main.MainWindowController;
import uk.co.primaltech.stockmanagement.database.DBConnection;

/**
 *
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 */
public class Main {
    
    private final static String name = "PrimalTech Stock Management";
    private final static String version = "v0.45";

    public static void main(String[] args) {

        Thread.currentThread().setName("Main Thread");

        /* Verify operating system. */
        if (!(isWindows() || isMac() || isUnix())) {
            System.out.println("Unsupported operating system: " + System.getProperty("os.name"));
            JOptionPane.showMessageDialog(MainWindowController.getInstance().getMainWindow(), "Primal Technology Stock Management is currently only available for Windows, Mac and Linux operating systems.", "Unsupported operating system", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }

        /* Load application settings. */
        ApplicationSettings.getInstance();
        
        /* Load Database */
        DBConnection.getInstance();
        
        /* Select OS look and feel. */
        setLookAndFeel();

        /* Create and display the form */
        MainWindowController mainController = MainWindowController.getInstance();
        MainWindow m = mainController.getMainWindow();
        m.setVisible(true);
    }

    public static String getName() {
        return name;
    }

    public static String getVersion() {
        return version;
    }

    private static boolean isWindows() {

        String os = System.getProperty("os.name").toLowerCase();
        // windows
        return (os.contains("win"));

    }

    private static boolean isMac() {

        String os = System.getProperty("os.name").toLowerCase();
        // Mac
        return (os.contains("mac"));

    }

    private static boolean isUnix() {
        String os = System.getProperty("os.name").toLowerCase();
        // linux or unix
        return (os.contains("nix") || os.contains("nux"));
    }

    private static void setLookAndFeel() {
        /* Select look and feel. */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        /* Set UI settings (background colours). */
        UIManager.put("TaskPane.useGradient", Boolean.TRUE);
        UIManager.put("TaskPane.backgroundGradientStart", new Color(153, 180, 209));
        UIManager.put("TaskPane.backgroundGradientEnd", new Color(190, 204, 218));
        UIManager.put("TaskPaneGroup.background", new Color(240, 240, 240));
    }

    
}