package uk.co.primaltech.stockmanagement.GUI.main;

import java.awt.Component;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JTabbedPane;
import uk.co.primaltech.stockmanagement.GUI.Search.SearchTab;
import uk.co.primaltech.stockmanagement.GUI.Search.SearchTabManager;
import uk.co.primaltech.stockmanagement.product.Product;

/**
 *
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 */
public class TabManager extends JTabbedPane {

    private static volatile TabManager instance = null;
        
    private final Map<ManagerType, List<Component>> tabList = new HashMap<>();
    
    private TabManager selectedTab;

    public static TabManager getInstance() {
        if (instance == null) {
            synchronized (TabManager.class) {
                if (instance == null) {
                    instance = new TabManager();
                    instance.addHomeTab();
                }
            }
        }        
        return instance;
    }

    protected TabManager() {
        setTabPlacement(JTabbedPane.BOTTOM);
        tabList.put(ManagerType.HOME_MANAGER, new ArrayList());
        tabList.put(ManagerType.SEARCH_MANAGER, new ArrayList());
        tabList.put(ManagerType.CLIENT_MANAGER, new ArrayList());
    }

    private void addHomeTab() {
        HomeTab homeTab = new HomeTab();
        instance.add("Home", homeTab);
        tabList.get(ManagerType.HOME_MANAGER).add(homeTab);
        selectedTab = null;
    }
    
    public void addSearchTab(Product product){
        assert product != null;
        
        SearchTab tab = SearchTabManager.getInstance().newSearchTab(product).getResultTab();
        
        instance.add(product.getProductName(), tab);
        
        //tweak for add close button
        instance.setTabComponentAt(instance.getTabCount() - 1, new ButtonTabComponent(instance));
        
        //sets the selected index for the current tab
        instance.setSelectedIndex(instance.getTabCount() - 1);
        
        tabList.get(ManagerType.SEARCH_MANAGER).add(tab);
        
        selectedTab = SearchTabManager.getInstance();
    }
    

    public boolean removeSearchTab() {
        if (!tabList.isEmpty()){
            Component selectedComponent = getSelectedComponent();
            
            //remove from global list
            tabList.get(ManagerType.SEARCH_MANAGER).remove(SearchTabManager.getInstance().getActiveSearchTab().getResultTab());            
            
            //remove from list of search tabs
            SearchTabManager.getInstance().removeSelectedTab();
            
            //remove from component list            
            instance.remove(selectedComponent);
            
            //set the index of the active component as the last open tab
            instance.setSelectedIndex(instance.getTabCount() - 1);
                        
            selectedTab = null;
            
            return true;
        }
        return false;
    }
    
    public TabManager getSelectedTab() {
        if (selectedTab == null) {
            return null;
        }
        if (selectedTab instanceof SearchTabManager){
            return(SearchTabManager) selectedTab;
        }
        
        return selectedTab;
    }
}