package uk.co.primaltech.stockmanagement.users;

/**
 *
 * @author Nuno Mogas <nuno.mogas@gmail.com>
 */
public class Address {
    
    private String city;
    private String state;
    private String street;
    private String zip;
    private String doorNumber;
    private String country;
    
    public Address(String city, String state, String street, String zip, String doorNumber, String country){
        this.city = city;
        this.state = state;
        this.street = street;
        this.zip = zip;
        this.doorNumber = doorNumber;
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getDoorNumber() {
        return doorNumber;
    }

    public void setDoorNumber(String doorNumber) {
        this.doorNumber = doorNumber;
    }   

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}