package uk.co.primaltech.stockmanagement.GUI.Search;

import uk.co.primaltech.stockmanagement.product.Product;

/**
 *
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 */
public class SearchTabLink {
       
    private final SearchTab searchTab;
    private final Product product;
    
    public SearchTabLink(Product product){
        assert product != null;        
        this.product = product;
        
        searchTab = new SearchTab(product);
    }    
    
    public SearchTab getResultTab() {
        return searchTab;
    }

    public Product getProduct() {
        return product;
    }
}