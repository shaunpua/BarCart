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


public class ItemFormActivity extends AppCompatActivity {

    private TextView barcode;
    private EditText nameInput;
    private EditText descInput;
    private Button createBtn,cancelBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_form);
        Intent intent = getIntent();
        String decoded = intent.getExtras().getString("barcode");
        barcode = findViewById(R.id.barcode);

        nameInput = findViewById(R.id.nameInputForm);
        descInput = findViewById(R.id.descriptionInputForm);
        createBtn = findViewById(R.id.createButton);
        cancelBtn = findViewById(R.id.cancelButton);

        barcode.setText(decoded);
        String Itemname = nameInput.getText().toString().trim();
        String Itemdesc = descInput.getText().toString().trim();

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
                //save to shared preferences and create entry in recycler view and then navigate to CartFragment
                if (Itemname.isEmpty()) {
                    nameInput.setError("Item Name is required!");
                    nameInput.requestFocus();
                    return;
                }else{
                    Intent intent = new Intent(ItemFormActivity.this, menuActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }

            }
        });

    }


}

