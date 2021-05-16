package ph.edu.dlsu.s12.barcart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView signupRedirect;
    private Button signinBtn;
    private EditText emailInput, passInput;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        signupRedirect = findViewById(R.id.signupText);
        signinBtn = findViewById(R.id.signinButton);
        emailInput = findViewById(R.id.emailInput);
        passInput = findViewById(R.id.passwordInput);

        signupRedirect.setOnClickListener(this);

        signinBtn.setOnClickListener(this);

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
            case R.id.signinButton:
                userLogin();
                break;

        }

    }

    private void userLogin() {

        String email = emailInput.getText().toString().trim();
        String password = passInput.getText().toString().trim();


        if (email.isEmpty()) {
            emailInput.setError("Email is required!");
            emailInput.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailInput.setError("Enter a valid email format");
            emailInput.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            passInput.setError("Password is required!");
            passInput.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    startActivity(new Intent(MainActivity.this, menuActivity.class));

                } else {
                    Toast.makeText(MainActivity.this, "Incorrect login credentials! Please try again!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}