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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class ItemFormActivity extends AppCompatActivity {

    private TextView barcode;
    private EditText nameInput;
    private EditText descInput;
    private Button createBtn,cancelBtn;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_form);
        mAuth = FirebaseAuth.getInstance();
        Intent intent = getIntent();
        String decoded = intent.getExtras().getString("barcode");
        barcode = findViewById(R.id.barcode);

        nameInput = findViewById(R.id.nameInputForm);
        descInput = findViewById(R.id.descriptionInputForm);
        createBtn = findViewById(R.id.createButton);
        cancelBtn = findViewById(R.id.cancelButton);

        barcode.setText(decoded);


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemFormActivity.this, menuActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Itemname = nameInput.getText().toString().trim();
                String Itemdesc = descInput.getText().toString().trim();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String user_ID =  user.getUid();
                //save to shared preferences and create entry in recycler view and then navigate to CartFragment
                if (nameInput.getText().toString().trim().isEmpty()) {
                    nameInput.setError("Item Name is required!");
                    nameInput.requestFocus();
                    return;
                }else{

                    Item item = new Item(Itemname, decoded,  Itemdesc, user_ID);
                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                    DocumentReference newItemRef = db
                            .collection("items")
                            .document();

                    Item itemAdd = new Item();
                    itemAdd.setProductName(Itemname);
                    itemAdd.setBarcode(decoded);
                    itemAdd.setProductDesc(Itemdesc);
                    itemAdd.setUserID(user_ID);

                    newItemRef.set(itemAdd).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(ItemFormActivity.this, "Successfully added item!", Toast.LENGTH_LONG).show();

                            } else {
                                Toast.makeText(ItemFormActivity.this, "Failed to add item!", Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                    /*
                    db.collection("items")
                            .add(item)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(ItemFormActivity.this, "Successfully added item!", Toast.LENGTH_LONG).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(ItemFormActivity.this, "Failed to add item!", Toast.LENGTH_LONG).show();
                                }
                            });
                    */

                    Intent intent = new Intent(ItemFormActivity.this, menuActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }

            }
        });

    }


}

