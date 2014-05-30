package uk.co.primaltech.stockmanagement.GUI.main;

import java.awt.Toolkit;
import java.net.URL;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import uk.co.primaltech.stockmanagement.GUI.Search.ProductSearchPanel;

/**
 *
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 */
public class HomeTab extends ContainerPanel {

    public HomeTab() {
        initializeComponent();
    }

    private void initializeComponent() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        Class mainWindowClass = MainWindowController.getInstance().getMainWindow().getClass();
        URL logoURL = mainWindowClass.getResource("/logo.png");
        ImageIcon logoicon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(logoURL).getScaledInstance(202, 62, 100));
        JLabel logo = new JLabel("", logoicon, JLabel.CENTER);

        panel.add(logo);
        panel.add(Box.createHorizontalStrut(30));
        panel.add(ProductSearchPanel.getInstance().getContentPanel());

        this.getContentPanel().setLayout(new BoxLayout(this.getContentPanel(), BoxLayout.Y_AXIS));
        this.getContentPanel().add(Box.createVerticalGlue());
        this.getContentPanel().add(panel);
        this.getContentPanel().add(Box.createVerticalGlue());
    }
}