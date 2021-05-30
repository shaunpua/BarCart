package ph.edu.dlsu.s12.barcart;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartItemActivity extends AppCompatActivity {
    private TextView cartName;
    private ArrayList<Item> itemList;
    private ArrayList<Cart> cartSelect;

    private RecyclerView recyclerView;

    private TextView cartDesc;
    private ImageView cartDelete;
    String name,desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.i("test", "REACHED");
        setContentView(R.layout.activity_cart_item);
        Log.i("test", "REACHED");
        recyclerView = findViewById(R.id.cartItemRecycler);
        cartDesc = findViewById(R.id.cartDesc_tv);
        cartName = findViewById(R.id.cartName_tv);
        cartDelete = findViewById(R.id.cartDelete);
        itemList= new ArrayList<>();


        Intent i = getIntent();
        String productString = i.getStringExtra("item_list");
        Gson gson = new Gson();


        Type type = new TypeToken<List<Item>>(){}.getType();

        itemList = gson.fromJson(productString, type);


        this.name=i.getStringExtra("cart_name");
        this.desc=i.getStringExtra("cart_desc");
        cartName.setText(name);
        cartDesc.setText(desc);
        setCartItemAdapter();

        cartDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String user_ID =  user.getUid();
                FirebaseFirestore db = FirebaseFirestore.getInstance();

                String docID = name + user_ID;

                DocumentReference delRef = db.collection("carts").document(docID);

                delRef.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){

                            Intent intent = new Intent(CartItemActivity.this, menuActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(CartItemActivity.this,"Failed to delete Cart!",Toast.LENGTH_SHORT).show();
                        }

                    }
                });



            }
        });




    }
    public void addItem(View view) {
        Intent i = new Intent(CartItemActivity.this,  AddItemActivity.class);
        i.putExtra("cart_name", name);
        i.putExtra("cart_desc", desc);
        startActivity(i);
    }

    private void getCartItems(){

        /*
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String user_ID =  user.getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        String docID = name + user_ID;

        DocumentReference docRef = db.collection("carts").document(docID);
           */
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        CollectionReference cartCollectionRef = db.collection("carts");

        Query cartQuery = cartCollectionRef.whereEqualTo("userIDC",  FirebaseAuth.getInstance().getCurrentUser().getUid()).whereEqualTo("cartName", name).whereEqualTo("cartDesc", desc);

        cartQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    for (QueryDocumentSnapshot document: task.getResult()) {
                        Cart cartGet = document.toObject(Cart.class);
                        cartSelect.add(cartGet);
                    }

                    itemList =  cartSelect.get(0).getItems();



                } else {

                }

            }
        });

        //docRef.get().

    }
    private void setCartItemAdapter() {
       
        cartAdapter adapter = new cartAdapter(itemList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}