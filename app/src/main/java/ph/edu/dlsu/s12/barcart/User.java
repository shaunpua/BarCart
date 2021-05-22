package ph.edu.dlsu.s12.barcart;

import java.util.ArrayList;

public class User {

    public String username, email;

    private ArrayList<Item> items;

    public User(){

    }

    public User(String email, String username, ArrayList<Item> items){
        this.username = username;
        this.email = email;
        this.items = items;

    }

    public String getEmail() {
            return email;
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Item> getItems() {return items;}
}
