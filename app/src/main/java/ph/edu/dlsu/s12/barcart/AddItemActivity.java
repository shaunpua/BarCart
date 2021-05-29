package ph.edu.dlsu.s12.barcart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AddItemActivity extends AppCompatActivity {

    private ArrayList<Item> item_add_List;
    private ArrayList<Item> selectedItems;
    private ArrayList<Cart> selectedCart;
    Button add_item_confirm_Button;
    RecyclerView add_item_recycler;
    private TextView cartName;
    private TextView cartDesc;
    String name,desc;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        add_item_confirm_Button=findViewById(R.id.item_confirm_btn);
        add_item_recycler=findViewById(R.id.add_item_recycler);
        cartName = findViewById(R.id.prevcartName_tv);
        cartDesc = findViewById(R.id.prevcartDesc_tv);

        item_add_List= new ArrayList<>();
        selectedItems = new ArrayList<>();
        selectedCart = new ArrayList<>();
        setAddItemAdapter();
        Intent i = getIntent();
        this.name=i.getStringExtra("cart_name");
        this.desc=i.getStringExtra("cart_desc");
        cartName.setText(name);
        cartDesc.setText(desc);
        getItems();

        add_item_confirm_Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                /*
                //note going to CartItemActivity is currently not possible because there is no way to determine what the previous cart selected was.
                Intent intent = new Intent(AddItemActivity.this, CartItemActivity.class);
                 */
                /*
                FirebaseFirestore db = FirebaseFirestore.getInstance();

               DocumentReference cartRef = db.collection("carts").getId()
                */
                //Query singleCartQuery = cartCollectionRef.whereEqualTo("cartName",  name.toString()).whereEqualTo("cartDesc",  desc.toString());

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String user_ID =  user.getUid();
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                String docID = name + user_ID;



                db.collection("carts").document(docID).update("items", selectedItems);

               //Query docQuer = db.collection("carts").whereEqualTo("cartName",  name.toString()).whereEqualTo("cartDesc",  desc.toString());






                Intent intent = new Intent(AddItemActivity.this, menuActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void getItems() {


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        CollectionReference itemCollectionRef = db.collection("items");

        Query itemsQuery = itemCollectionRef.whereEqualTo("userID",  FirebaseAuth.getInstance().getCurrentUser().getUid());

        itemsQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document: task.getResult()) {
                        Item itemGet = document.toObject(Item.class);
                        item_add_List.add(itemGet);
                    }

                    setAddItemAdapter();

                } else {




                }

            }
        });

    }
    private void setAddItemAdapter() {

        /*
        item_add_List.add(new Item(
                "Iphone 12",
                "14444124124",
                "This is a scam by steve jobs",
                "123456"
        ));



        item_add_List.add(new Item(
                "Iphone 12",
                "14444124124",
                "This is a scam by steve jobs",
                "123456"
        ));


         */
        addItemAdapter adapter = new addItemAdapter(item_add_List);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        add_item_recycler.setLayoutManager(layoutManager);
        add_item_recycler.setItemAnimator(new DefaultItemAnimator());
        add_item_recycler.setAdapter(adapter);

        selectedItems = adapter.getSelectedItems();


    }
}
