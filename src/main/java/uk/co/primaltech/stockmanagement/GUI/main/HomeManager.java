package uk.co.primaltech.stockmanagement.GUI.main;


/**
 *
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 */
public class HomeManager extends TabManager{
    
    private static volatile HomeManager instance = null;
    
    private final HomeTabLink defaultTabLink;
    
    public static HomeManager getInstance(){
        if (instance == null){
            synchronized(HomeManager.class){
                if (instance == null){
                    instance = new HomeManager();                    
                }
            }
        }
        return instance;
    }
    
    private HomeManager(){
        defaultTabLink = new HomeTabLink();
    }

    public HomeTabLink getDefaultTabLink() {
        return defaultTabLink;
    }
}