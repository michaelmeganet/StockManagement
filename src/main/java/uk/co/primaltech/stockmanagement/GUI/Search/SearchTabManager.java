package uk.co.primaltech.stockmanagement.GUI.Search;

import java.util.ArrayList;
import java.util.List;
import uk.co.primaltech.stockmanagement.GUI.main.TabManager;
import uk.co.primaltech.stockmanagement.product.Product;

/**
 *
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 */
public class SearchTabManager extends TabManager{
    
    /* Singleton instance. */
    private static volatile SearchTabManager instance = null;
    
    /* List of opened search tabs */
    private List<SearchTabLink> searchTabList;
    
    /* Selected search tab */
    private SearchTabLink selectedTab;
    
    /* Singleton instance with double-check lock to prevent concurrenct accesses */
    public static SearchTabManager getInstance(){
        if (instance == null){
            synchronized(SearchTabManager.class){
                if (instance == null){
                    instance = new SearchTabManager();
                }
            }
        }
        return instance;
    }
    
    /* Private constructor to prevent instantiation from outside the class  */
    private SearchTabManager(){
        searchTabList = new ArrayList<>();
    }
    
    public SearchTabLink newSearchTab(Product product){
        assert product != null;
        
        SearchTabLink newTab = new SearchTabLink(product);
        searchTabList.add(newTab);
        selectedTab = newTab;
        return selectedTab;
    }        
        
    public void removeSelectedTab(){
        if (selectedTab != null && !searchTabList.isEmpty()){
            //remove the tab for the list of opened tabs
            searchTabList.remove(selectedTab);
            
            if (!searchTabList.isEmpty()){
                //mark the last inserted tab as the new selected one
                selectedTab = searchTabList.get(searchTabList.size() - 1);
            }else{
                selectedTab = null;
            }
        }
    }
    
    public void setSelectedTab(String title){
        assert title != null && !title.isEmpty();        
    }

    public List<SearchTabLink> getSearchTabList() {
        return searchTabList;
    }

    public void setSearchTabList(List<SearchTabLink> searchTabList) {
        this.searchTabList = searchTabList;
    
    }

    public SearchTabLink getActiveSearchTab() {
        return selectedTab;
    }
}