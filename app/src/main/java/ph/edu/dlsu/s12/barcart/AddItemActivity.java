package ph.edu.dlsu.s12.barcart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AddItemActivity extends AppCompatActivity {

    private ArrayList<Item> item_add_List;
    Button add_item_confirm_Button;
    RecyclerView add_item_recycler;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        add_item_confirm_Button=findViewById(R.id.item_confirm_btn);
        add_item_recycler=findViewById(R.id.add_item_recycler);
        item_add_List= new ArrayList<>();
        setAddItemAdapter();

        add_item_confirm_Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                /*
                //note going to CartItemActivity is currently not possible because there is no way to determine what the previous cart selected was.
                Intent intent = new Intent(AddItemActivity.this, CartItemActivity.class);
                 */
                Intent intent = new Intent(AddItemActivity.this, menuActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
    private void setAddItemAdapter() {


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
        addItemAdapter adapter = new addItemAdapter(item_add_List);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        add_item_recycler.setLayoutManager(layoutManager);
        add_item_recycler.setItemAnimator(new DefaultItemAnimator());
        add_item_recycler.setAdapter(adapter);
    }
}
