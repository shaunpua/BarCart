package ph.edu.dlsu.s12.barcart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddCartActivity extends AppCompatActivity {

    EditText cartNameInput;
    Button confirmButton,cancel_Button;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cart);
        cartNameInput=findViewById(R.id.cartNameInput);
        cancel_Button=findViewById(R.id.cancel_Button);
        confirmButton=findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String cartName=cartNameInput.getText().toString();
                if(cartName.trim().isEmpty())
                {
                    Toast.makeText(AddCartActivity.this,"Field cannot be empty!",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AddCartActivity.this,"New Cart Created!",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddCartActivity.this, menuActivity.class);

                    intent.putExtra("cartName",cartName);
                    startActivity(intent);
                }

            }
        });
        cancel_Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(AddCartActivity.this, menuActivity.class);
                startActivity(intent);
            }
        });
    }
}
