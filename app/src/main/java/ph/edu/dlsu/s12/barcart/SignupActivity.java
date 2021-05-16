package ph.edu.dlsu.s12.barcart;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;

    private TextView signinRedirect;
    private Button signupBtn;
    private EditText emailInputR, passInputR, confirmInputR, nameInputR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();

        signinRedirect = findViewById(R.id.signinText);

        signupBtn = findViewById(R.id.signupButton);

        emailInputR = findViewById(R.id.emailInputR);
        passInputR = findViewById(R.id.passwordInputR);
        confirmInputR = findViewById(R.id.confirmInputR);
        nameInputR = findViewById(R.id.nameInputR);

        signinRedirect.setOnClickListener(this);
        signupBtn.setOnClickListener(this);
        /*
        signinRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        */

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signinText:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.signupButton:
                registerUser();
                break;
        }

    }

    private void registerUser() {
        String email = emailInputR.getText().toString().trim();
        String username = nameInputR.getText().toString().trim();
        String password = passInputR.getText().toString().trim();
        String cpassword = confirmInputR.getText().toString().trim();

        if (username.isEmpty()) {
            nameInputR.setError("Username is required!");
            nameInputR.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            emailInputR.setError("Email is required!");
            emailInputR.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailInputR.setError("Enter a valid email format");
            emailInputR.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passInputR.setError("Password is required!");
            passInputR.requestFocus();
            return;
        }

        if (password.length() < 6){
            passInputR.setError("Password must be at least 6 characters!");
            passInputR.requestFocus();
            return;

        }

        if (cpassword.isEmpty()) {
            confirmInputR.setError("Password is required!");
            confirmInputR.requestFocus();
            return;
        }


    }
}