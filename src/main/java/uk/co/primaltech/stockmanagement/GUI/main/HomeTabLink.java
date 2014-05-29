package uk.co.primaltech.stockmanagement.GUI.main;

/**
 *
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 */
public class HomeTabLink {
    
    private final HomeTabManager homeTabManager;
    
    public HomeTabLink(){
        homeTabManager = TabManager.getInstance().addHomeTab();
    }

    public HomeTabManager getHomeTabManager() {
        return homeTabManager;
    }
}