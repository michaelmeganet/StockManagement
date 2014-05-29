package uk.co.primaltech.stockmanagement.GUI.main;

import com.l2fprod.common.swing.JTaskPane;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

/**
 *
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 */
public class ContainerPanel extends JPanel {
    
    /* List of panels in this container. */
    private final List<ContentPanel> contentPanels;

    /* Panel to take all the content panels inside. */
    private final JTaskPane contentPanel;
    private final JScrollPane contentScrollPane;
    /* Scroll bar to control content panels. */
    private final JScrollBar globalScrollBar = null;
    static protected int biggestSize = 0;        

    /* Constructor. */
    public ContainerPanel() {
        /* Box layout to display content panels in a page axis (vertically). */
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        contentPanels = new ArrayList<>();

        /* Create content panel to take all the contentPanels. */
        contentPanel = new JTaskPane();
        contentScrollPane = new JScrollPane(contentPanel);
        contentScrollPane.setBorder(null);
        this.add(contentScrollPane);
    }

    /* Add a content panel to this panel. */
    public synchronized void addContentPanel(ContentPanel panel, int position) {
        assert panel != null;
        assert contentPanels != null;

        contentPanel.add(panel.getContentPanel(), position);

        /* Add panel to list of panels. */
        contentPanels.add(panel);

     
        /* Update graphics to show new panel. */
        panel.updateUI();
        this.updateUI();
    }

    public void addComponent(Component c) {
        assert c != null;
        this.add(c);
        this.updateUI();
    }
    
    /* Remove a content panel from this panel. */
    public synchronized void removeContentPanel(ContentPanel panel) {
        assert panel != null;
        assert contentPanels != null;
        assert !contentPanels.isEmpty();
        assert contentPanels.contains(panel);

        contentPanel.remove(panel.getContentPanel());
        contentPanels.remove(panel);
        panel.deletePanel();

        this.updateUI();
    }  

    public JScrollBar getScrollBar() {
        assert globalScrollBar != null;

        return globalScrollBar;
    }
    
    public JTaskPane getContentPanel() {
        return contentPanel;
    }                    
}