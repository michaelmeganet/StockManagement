package uk.co.primaltech.stockmanagement.GUI.main;

/**
 *
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 */
public class HomeTabManager extends TabManager{
    
    private final HomeTab homeTab;

    public HomeTabManager(){
        homeTab = new HomeTab();
    }
    
    public HomeTab getHometab() {
        return homeTab;
    }
}