package ph.edu.dlsu.s12.barcart;
import java.util.ArrayList;

public class Cart {
    private String cartName;
    private ArrayList<Item> items;
    public Cart( String cartName, ArrayList<Item> items) {

        this.cartName = cartName;
        this.items = items;


    }
    public String getCartName() {
        return cartName;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
