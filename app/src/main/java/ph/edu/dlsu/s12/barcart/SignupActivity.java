package ph.edu.dlsu.s12.barcart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignupActivity extends AppCompatActivity {

    private TextView signinRedirect;
    private Button signupBtn;
    private EditText emailInputR, passInputR, confirmInputR, nameInputR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signinRedirect = findViewById(R.id.signinText);

        signupBtn = findViewById(R.id.signupButton);

        emailInputR = findViewById(R.id.emailInputR);
        passInputR = findViewById(R.id.passwordInputR);
        confirmInputR = findViewById(R.id.confirmInputR);
        nameInputR = findViewById(R.id.nameInputR);

        signinRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}