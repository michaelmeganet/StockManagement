package uk.co.primaltech.stockmanagement.GUI.Search;

import uk.co.primaltech.stockmanagement.GUI.main.TabManager;
import uk.co.primaltech.stockmanagement.product.Product;

/**
 *
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 */
public class SearchResultsTabManager extends TabManager{
    
    private final Product product;
    private final SearchResultsTab resultsTab;
    
    public SearchResultsTabManager(Product product){
        assert product != null;
        this.product = product;
        
        resultsTab = new SearchResultsTab(product);
    }

    public Product getProduct() {
        return product;
    }

    public SearchResultsTab getResultsTab() {
        return resultsTab;
    }
}