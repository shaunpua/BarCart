package ph.edu.dlsu.s12.barcart;

public class Item {
    private String name, barcode,desc, userID;
    //private int itempos;


    public Item( String name, String barcode, String desc, String userID) {

        this.name = name;
        this.barcode = barcode;
        this.desc = desc;
        this.userID = userID;

    }
    public String getProductName() {
        return name;
    }
    public String getBarcode() {
        return barcode;
    }
    public String getProductDesc() {
        return desc;
    }
    public String getUserID() {return userID;}
    /*public int getitempos() {return itempos;}

     */
}
