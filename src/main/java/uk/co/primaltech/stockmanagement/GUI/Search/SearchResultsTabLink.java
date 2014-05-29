package uk.co.primaltech.stockmanagement.GUI.Search;

import uk.co.primaltech.stockmanagement.GUI.main.TabManager;
import uk.co.primaltech.stockmanagement.product.Product;

/**
 *
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 */
public class SearchResultsTabLink {
    
    private final Product product;
    private final SearchResultsTabManager tabManager;
    
    public SearchResultsTabLink(Product product){
        assert product != null;
        this.product = product;
        
        tabManager = TabManager.getInstance().addSearchResultsTab(product);
    }

    public Product getProduct() {
        return product;
    }

    public SearchResultsTabManager getTabManager() {
        return tabManager;
    }
}