package uk.co.primaltech.stockmanagement.GUI.main;

import javax.swing.JTabbedPane;
import uk.co.primaltech.stockmanagement.GUI.Search.SearchTabManager;

import uk.co.primaltech.stockmanagement.product.Product;

/**
 *
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 */
public class TabManager extends JTabbedPane {

    private static volatile TabManager instance = null;
//    private final List<Tuple<TabManager, Component>> tabList;
    private TabManager selectedTab;
//    private boolean initListenner = false;

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
//        tabList = new ArrayList<>();
        setTabPlacement(JTabbedPane.BOTTOM);                
    }

    private void addHomeTab() {
        instance.add("Home", new HomeTab());
    }
    
    public void addSearchTab(Product product){
        instance.add("Search", SearchTabManager.getInstance().newSearchTab(product).getResultTab());
        
        //tweak for add close button
        instance.setTabComponentAt(instance.getTabCount() - 1, new ButtonTabComponent(instance));
        
        //sets the selected index for the current tab
        instance.setSelectedIndex(instance.getTabCount() - 1);
    }
    

    public boolean removeSearchTabResult() {
//        if (tabList != null && !tabList.isEmpty()) {
//            Component selectedComponent = getSelectedComponent();
//            if (selectedComponent instanceof SearchResultsTab) {
//                SearchTabManager.getInstance().removeSelectedTab();
//                for (int i = 1; i < tabList.size(); i++) {
//                    if (tabList.get(i).getY() == selectedComponent) {
//                        tabList.remove(i);
//                        instance.remove(selectedComponent);
//                        instance.setSelectedIndex(instance.getTabCount() - 1);
//                        selectedTab = tabList.get(tabList.size() - 1).getX();
//                        return true;
//                    }
//                }
//            }
//        }
        return false;
    }
    
    public TabManager getSelectedTab() {
        if (selectedTab == null) {
            return null;
        }
        return selectedTab;
    }
}
