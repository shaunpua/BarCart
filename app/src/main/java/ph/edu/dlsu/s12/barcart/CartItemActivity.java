package ph.edu.dlsu.s12.barcart;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CartItemActivity extends AppCompatActivity {
    private TextView cartName;

    private ArrayList<Item> itemList;

    private RecyclerView recyclerView;

    private TextView cartDesc;

    private FloatingActionButton Item_add_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.i("test", "REACHED");
        setContentView(R.layout.activity_cart_item);
        Log.i("test", "REACHED");
        recyclerView = findViewById(R.id.cartItemRecycler);
        cartDesc = findViewById(R.id.cartDesc_tv);
        itemList= new ArrayList<>();

        cartName = findViewById(R.id.cartName_tv);
        Item_add_btn = findViewById(R.id.Item_add_btn);
        Intent i = getIntent();
        String productString = i.getStringExtra("item_list");
        Gson gson = new Gson();


        Type type = new TypeToken<List<Item>>(){}.getType();

        itemList = gson.fromJson(productString, type);



        cartName.setText(i.getStringExtra("cart_name"));

        setCartItemAdapter();




    }
    public void addItem(View view) {
        startActivity(new Intent(CartItemActivity.this, AddItemActivity.class));
    }
    private void setCartItemAdapter() {
        itemList.add(new Item(
                "Iphone 12",
                "14444124124",
                "This is a scam by steve jobs",
                "123456"
        ));
        cartAdapter adapter = new cartAdapter(itemList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}