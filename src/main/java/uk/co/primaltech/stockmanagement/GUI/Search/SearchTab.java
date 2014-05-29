package uk.co.primaltech.stockmanagement.GUI.Search;

import uk.co.primaltech.stockmanagement.GUI.main.ContainerPanel;
import uk.co.primaltech.stockmanagement.GUI.product.ProductInformationPanel;
import uk.co.primaltech.stockmanagement.product.Product;

/**
 *
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 */
public class SearchTab extends ContainerPanel  {
    
    private final Product product;
    
    public SearchTab(Product product){
        this.product = product;
        initializeComponent();
    }    
    
    private void initializeComponent(){
        
        /* product info panel */
        ProductInformationPanel productInfo = new ProductInformationPanel(product);
        addContentPanel(productInfo, 0);
    }    

    public Product getProduct() {
        return product;
    }        
}
