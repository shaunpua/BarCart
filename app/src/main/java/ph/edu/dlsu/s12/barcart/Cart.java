package ph.edu.dlsu.s12.barcart;
import java.util.ArrayList;

public class Cart {
    private String cartName, cartDesc, userIDC;
    private ArrayList<Item> items;

    public Cart(){

    }
    public Cart( String cartName, String cartDesc, String userIDC, ArrayList<Item> items) {

        this.cartName = cartName;
        this.cartDesc = cartDesc;
        this.userIDC = userIDC;
        this.items = items;


    }
    public String getCartName() {
        return cartName;
    }

    public String getCartDesc() {
        return cartDesc;
    }

    public String getUserIDC(){
        return userIDC;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setCartName(String cartName){
        this.cartName = cartName;
    }

    public void setCartDesc(String cartDesc){
        this.cartDesc= cartDesc;
    }

    public void setUserIDC(String userIDC){
        this.userIDC = userIDC;
    }

    public void setItems(ArrayList<Item> items){
        this.items = items;
    }
}
