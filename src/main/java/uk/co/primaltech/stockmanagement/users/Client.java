package uk.co.primaltech.stockmanagement.users;

import uk.co.primaltech.stockmanagement.product.Product;

/**
 *
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 */
public class Client extends User{
    
    private final Product product;

    public Client(String name, Address address, Product product) {
        super(name, address);
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }
}