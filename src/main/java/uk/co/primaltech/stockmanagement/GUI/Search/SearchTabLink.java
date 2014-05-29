package uk.co.primaltech.stockmanagement.GUI.Search;

import uk.co.primaltech.stockmanagement.product.Product;

/**
 *
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 */
public class SearchTabLink {
       
    private final SearchTab searchTab;
    private Product product;
    
    public SearchTabLink(Product product){
        assert product != null;        
        this.product = product;
        
//        tabManager = TabManager.getInstance().addSearchResultsTab(product);
        searchTab = new SearchTab(product);
    }    
    
    public SearchTab getResultTab() {
        return searchTab;
    }
                

//    public SearchResultsTabManager getTabManager() {
//        return tabManager;
//    }

    public Product getProduct() {
        return product;
    }

    
}