package ph.edu.dlsu.s12.barcart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

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

        if (!password.equals(cpassword)){
            passInputR.setError("Passwords must be the same");
            confirmInputR.setError("Passwords must be the same!");
            passInputR.requestFocus();
            return;


        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            User user = new User(email, username);
                            Toast.makeText(SignupActivity.this, "User has been successfully authenticaed!", Toast.LENGTH_LONG).show();
                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            db.collection("users")
                                    .add(user)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Toast.makeText(SignupActivity.this, "User has been successfully registered!", Toast.LENGTH_LONG).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(SignupActivity.this, "Failed to register, try again!", Toast.LENGTH_LONG).show();
                                        }
                                    });

                            /*
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()){
                                            Toast.makeText(SignupActivity.this, "User has been successfully registered!", Toast.LENGTH_LONG).show();

                                        } else {
                                            Toast.makeText(SignupActivity.this, "Failed to register, try again!", Toast.LENGTH_LONG).show();
                                        }

                                    }
                            });
                            */

                            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(SignupActivity.this, "Failed to register, try again!", Toast.LENGTH_LONG).show();
                        }
                    }

        });




    }
}