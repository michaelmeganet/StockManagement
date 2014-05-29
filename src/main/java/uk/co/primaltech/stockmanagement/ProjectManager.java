package uk.co.primaltech.stockmanagement;

import uk.co.primaltech.stockmanagement.GUI.Search.SearchManager;

/**
 *
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 */
public class ProjectManager {
    
    private static volatile ProjectManager instance = null;
    
    /* Reference to the active manager */
    private static ProjectManager selectedManager;
    
    public static ProjectManager getInstance(){
        if (instance == null){
            synchronized(ProjectManager.class){
                if (instance == null){
                    instance = new ProjectManager();
                }
            }
        }
        return instance;
    }
    
    /* protected constructor to prevent instantiation */
    protected ProjectManager() {
    }
    
    public void setSelectedManager(ProjectManager manager){
        assert manager != null;
        selectedManager = manager;
    }

    public static ProjectManager getSelectedManager() {
        return selectedManager;
    }        
    
    public SearchManager getSearchManager(){
        return SearchManager.getInstance();
    }
}