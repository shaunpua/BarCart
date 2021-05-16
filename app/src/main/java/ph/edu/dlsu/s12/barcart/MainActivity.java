package ph.edu.dlsu.s12.barcart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView signupRedirect;
    private Button signinBtn;
    private EditText emailInput, passInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signupRedirect = findViewById(R.id.signupText);
        signinBtn = findViewById(R.id.signinButton);
        emailInput = findViewById(R.id.emailInput);
        passInput = findViewById(R.id.passwordInput);

        signupRedirect.setOnClickListener(this);

        /*

        signupRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });


        */


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signupText:
                startActivity(new Intent(this, SignupActivity.class));
                break;
        }

    }
}