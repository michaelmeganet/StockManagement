package uk.co.primaltech.stockmanagement;

/**
 * Class used for construct a tupple
 * 
 * @author Nuno Silva       -   39877
 * 
 * @param <X>
 * @param <Y> 
 */
public class Tuple<X, Y>{

    /**
     * First tuple value
     * 
     * @serialField x
     */
    private X x;
    
    /**
     * Second tuple value
     * 
     * @serialField y
     */
    private Y y;

    /**
     * Tuple constructor
     * 
     * @param x first value
     * @param y second value
     */
    public Tuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Getter
     * @return actual value o X
     */
    public X getX() {
        return this.x;
    }
    /**
     * Getter
     * @return actual value o Y
     */
    public Y getY() {
        return this.y;
    }

    /**
     * Setter
     * @param x sets a new value for X
     */
    public void setX(X x) {
        this.x = x;
    }
    
    /**
     * Setter
     * @param y sets a new value for Y
     */
    public void setY(Y y) {
        this.y = y;
    }
}