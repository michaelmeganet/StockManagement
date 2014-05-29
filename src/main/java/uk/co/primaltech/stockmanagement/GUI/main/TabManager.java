package uk.co.primaltech.stockmanagement.GUI.main;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import uk.co.primaltech.stockmanagement.GUI.Search.SearchManager;
import uk.co.primaltech.stockmanagement.GUI.Search.SearchResultsTab;
import uk.co.primaltech.stockmanagement.GUI.Search.SearchResultsTabManager;
import uk.co.primaltech.stockmanagement.ProjectManager;
import uk.co.primaltech.stockmanagement.Tuple;
import uk.co.primaltech.stockmanagement.product.Product;

/**
 *
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 */
public class TabManager extends JTabbedPane {

    private static volatile TabManager instance = null;
    private final List<Tuple<TabManager, Component>> tabList;
    private TabManager selectedTab;
    private boolean initListenner = false;

    public static TabManager getInstance() {
        if (instance == null) {
            synchronized (TabManager.class) {
                if (instance == null) {
                    instance = new TabManager();
                }
            }
        }
        return instance;
    }

    protected TabManager() {
        tabList = new ArrayList<>();
        setTabPlacement(JTabbedPane.BOTTOM);
    }

    public final HomeTabManager addHomeTab() {
        HomeTabManager homeTab = new HomeTabManager();
        tabList.add(new Tuple<TabManager, Component>(homeTab, homeTab.getHometab()));

        instance.addTab("Home", homeTab.getHometab());        
        instance.setSelectedIndex(instance.getTabCount() - 1);
        if (initListenner == false) {
            initListenner = true;
            addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    JTabbedPane pane = (JTabbedPane) e.getSource();
                    TabManager.getInstance().setSelectedTab(pane.getComponentAt(pane.getSelectedIndex()));
                }
            });
        }
        selectedTab = homeTab;
        return homeTab;
    }    

    public final SearchResultsTabManager addSearchResultsTab(Product product) {
        SearchResultsTabManager searchTab = new SearchResultsTabManager(product);
        tabList.add(new Tuple<TabManager, Component>(searchTab, searchTab.getResultsTab()));
        
        String productName = product.getProductName();
        String tabName;
        if (productName.length() <= 10){
            tabName = productName.concat(" - ").concat(product.getBrand()).concat(" - ").concat(product.getSerial());
        }else{
            tabName = productName.substring(0, 10).concat(" - ").concat(product.getBrand()).concat(" - ").concat(product.getSerial());
        }
        
        instance.add(tabName, searchTab.getResultsTab());                
        
        //tweak for add close button
        instance.setTabComponentAt(instance.getTabCount() - 1, new ButtonTabComponent(instance));
        
        instance.setSelectedIndex(instance.getTabCount() - 1);                

        selectedTab = searchTab;
        return searchTab;
    }

public boolean removeSearchTabResult() {
        if (tabList != null && !tabList.isEmpty()) {
            Component selectedComponent = getSelectedComponent();
            if (selectedComponent instanceof SearchResultsTab) {
                SearchManager.getInstance().removeSelectedTab();
                for (int i = 1; i < tabList.size(); i++) {
                    if (tabList.get(i).getY() == selectedComponent) {
                        tabList.remove(i);
                        instance.remove(selectedComponent);
                        instance.setSelectedIndex(instance.getTabCount() - 1);
                        selectedTab = tabList.get(tabList.size() - 1).getX();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * \
     * Sets the new active TabManager (TAB)
     *
     * @param tab Tab Manager to be marked as active
     */
    private void setSelectedTab(final Component component) {
        assert component != null;

        Runnable r = new Runnable() {
            @Override
            public void run() {
                if (component instanceof HomeTab) {
                    ProjectManager.getInstance().setSelectedManager(HomeManager.getInstance());
                    selectedTab = (HomeTabManager) tabList.get(0).getX();
                } else if (component instanceof SearchResultsTab) {
                    ProjectManager.getInstance().setSelectedManager(SearchManager.getInstance());
                    for (int i = 1; i < tabList.size(); i++) {
                        if (tabList.get(i).getY() == component) {
                            selectedTab = (SearchResultsTabManager) tabList.get(i).getX();
                            break;
                        }
                    }

                }
            }
        };
        SwingUtilities.invokeLater(r);
    }

    public TabManager getSelectedTab() {
        if (selectedTab == null) {
            return null;
        }
        return selectedTab;
    }
}