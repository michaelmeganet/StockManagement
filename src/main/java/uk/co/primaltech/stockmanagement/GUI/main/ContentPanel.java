package uk.co.primaltech.stockmanagement.GUI.main;

import com.l2fprod.common.swing.JTaskPaneGroup;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.UIManager;

/**
 * @author Paulo Gaspar
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 */
public abstract class ContentPanel extends JPanel
{
    /* Flag to indicate if this content panel needs a global scroll bar. */
    private boolean scrollbarPolicy;

    /* Exterior panels just for graphical purposes.*/
    protected JTaskPaneGroup contentPanel = null;

    /* Panel title. */
    private String title;
    
    static Color defaultColour1 = (Color) UIManager.get("TaskPaneGroup.titleBackgroundGradientStart");
    static Color defaultColour2 = (Color) UIManager.get("TaskPaneGroup.titleBackgroundGradientEnd");
   
    /* Constructor with a specialType argument that allows choosing a type/colour for the content pane. */
    public ContentPanel(String title, boolean globalScroolbar, int specialType)
    {
        assert title != null;

        this.title = title;
        this.scrollbarPolicy = globalScroolbar;
        
        boolean hasSpecialType = (specialType != 0);
        Color c1 = null, c2 = null;
        
        switch(specialType)
        {
            case 1 : c1 = defaultColour1; c2 = defaultColour2; break;
            case 2 : c1 = new Color(255, 255, 255); c2 = new Color(253, 245, 147); break;
        }
        
        /* Change colours if needed. */
        if (hasSpecialType)
        {
            UIManager.put("TaskPaneGroup.titleBackgroundGradientStart", c1);
            UIManager.put("TaskPaneGroup.titleBackgroundGradientEnd", c2);
        }
        
        /* Create the task pane group. This is the nice looking panel that allows collapsing and closing. */
        contentPanel = new JTaskPaneGroup();                
        contentPanel.setLayout(new BorderLayout()); //border layout to make components use the maximum available space (CENTER zone)
        contentPanel.add(this, BorderLayout.CENTER);
        contentPanel.setTitle(title);        
//        contentPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
//        this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        
        /* Replace defaults. */
        if (hasSpecialType)
        {
            UIManager.put("TaskPaneGroup.titleBackgroundGradientStart", defaultColour1);
            UIManager.put("TaskPaneGroup.titleBackgroundGradientEnd", defaultColour2);
        }
    }
    
    public ContentPanel(String title, boolean globalScroolbar)
    {
        this(title, globalScroolbar, 0);
    }
    
    public void eraseBorder()
    {
        ((JPanel)contentPanel.getContentPane()).setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    }
    

    public synchronized boolean needsScrollbar() { return scrollbarPolicy; }
    public synchronized int getMaxCharacters() { return 0; }
    public synchronized void addPadding(int finalSize) {}
    public void remakePanel() {}
    public void deletePanel() {}
    
    public String getTitle() 
    {
        return title;
    }

    public void setTitle(String title) 
    {
        this.title = title;

        /* IF there is an exterior panel, update its title. */
        if (contentPanel != null)
        {
            contentPanel.setTitle(title);
            contentPanel.updateUI();
        }
    }

    /* Mark this panel border as the selected panel of this project. */
    public synchronized void setSelected(boolean flag)
    {
        assert contentPanel != null;
        
        contentPanel.setSpecial(flag);
    }

    void setExteriorPanel(JTaskPaneGroup actionPane)
    {
        assert actionPane != null;

        this.contentPanel = actionPane;
    }

    public JTaskPaneGroup getContentPanel()
    {
        return contentPanel;
    }        
}