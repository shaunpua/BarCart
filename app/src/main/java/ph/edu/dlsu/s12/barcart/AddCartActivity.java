package ph.edu.dlsu.s12.barcart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AddCartActivity extends AppCompatActivity {

    EditText cartNameInput, cartDescInput;
    Button confirmButton,cancel_Button;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cart);
        cartNameInput=findViewById(R.id.cartNameInput);
        cartDescInput =findViewById(R.id.cartDescInput);
        cancel_Button=findViewById(R.id.cancel_Button);
        confirmButton=findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String cartName=cartNameInput.getText().toString();
                String cartDesc=cartDescInput.getText().toString();
                ArrayList<Item> blankItemList = new ArrayList<>();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String user_ID =  user.getUid();
                if(cartName.trim().isEmpty())
                {
                    Toast.makeText(AddCartActivity.this,"Field cannot be empty!",Toast.LENGTH_SHORT).show();
                }
                else{
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    String docID = cartName + user_ID;
                    DocumentReference newCartRef = db
                            .collection("carts")
                            .document(docID);

                    Cart cartAdd = new Cart();
                    cartAdd.setCartName(cartName);
                    cartAdd.setCartDesc(cartDesc);
                    cartAdd.setUserIDC(user_ID);
                    cartAdd.setItems(blankItemList);

                    newCartRef.set(cartAdd).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(AddCartActivity.this, "Successfully added cart!", Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(AddCartActivity.this, "Failed to add cart!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                    //Toast.makeText(AddCartActivity.this,"New Cart Created!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddCartActivity.this, menuActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }

            }
        });
        cancel_Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AddCartActivity.this, menuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
}
