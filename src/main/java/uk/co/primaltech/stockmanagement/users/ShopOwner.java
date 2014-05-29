package uk.co.primaltech.stockmanagement.users;

/**
 *
 * This class is for invoice purpose
 * 
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 */
public class ShopOwner extends User{
    
    private final int phone;
    private final String companyName;
    

    public ShopOwner(String name, Address address, int phone, String  companyName) {
        super(name, address);
        this.phone = phone;
        this.companyName = companyName;
    }    
}