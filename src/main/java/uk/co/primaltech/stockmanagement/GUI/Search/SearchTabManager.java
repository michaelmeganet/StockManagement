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
    private List<SearchResultsTabLink> searchTabList;
    
    /* Selected search tab */
    private SearchResultsTabLink selectedTab;
    
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
    
    public SearchResultsTabLink newSearchTab(Product product){
        assert product != null;
        
        SearchResultsTabLink newTab = new SearchResultsTabLink(product);
        searchTabList.add(newTab);
        selectedTab = newTab;
        return newTab;
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

    public List<SearchResultsTabLink> getSearchTabList() {
        return searchTabList;
    }

    public void setSearchTabList(List<SearchResultsTabLink> searchTabList) {
        this.searchTabList = searchTabList;
    }
}