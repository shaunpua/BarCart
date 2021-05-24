package ph.edu.dlsu.s12.barcart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
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

import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView signupRedirect;
    private Button signinBtn;
    private EditText emailInput, passInput;

    private FirebaseAuth mAuth;

    private int REQUEST_CODE =101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        signupRedirect = findViewById(R.id.signupText);
        signinBtn = findViewById(R.id.signinButton);
        emailInput = findViewById(R.id.emailInput);
        passInput = findViewById(R.id.passwordInput);
        /*
        INSERT CODE FOR NOTIFICATION AND DATA CHECKING HERE
        //site below to click a button that lets you schedule alarms and mayb notifs(?) to appear on a certain date of the year
         https://www.tutorialspoint.com/how-to-set-an-android-notification-to-a-specific-date-in-the-future
         */
        /*


        //checking date
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        Toast.makeText(this,"Today's Date: " + currentYear + currentMonth + currentDay, Toast.LENGTH_SHORT).show();
        //making a notif
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            NotificationChannel channel=new NotificationChannel("Barcart Notification","NotificationChannel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this,"Barcart Notification");
        builder.setContentTitle("BarCart App");
        builder.setContentText("One or more carts in need of restocking!");
        builder.setSmallIcon(R.drawable.ic_notifications);
        builder.setAutoCancel(true);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
        managerCompat.notify(0,builder.build());
        */
        /*
        END NOTIFICATION CHECKING HERE
         */
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
                    //request permission
                    if(ContextCompat.checkSelfPermission(MainActivity.this,
                            Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED){
                        Intent intent = new Intent(MainActivity.this, menuActivity.class);

                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                    else{
                       requestCameraPermission();
                    }



                } else {
                    Toast.makeText(MainActivity.this, "Incorrect login credentials! Please try again!", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
    public void requestCameraPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)){
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed to scan barcodes using the back camera of your device.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.CAMERA},REQUEST_CODE);

                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }else{
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CAMERA},REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==REQUEST_CODE){
            if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this,"Permission GRANTED",Toast.LENGTH_SHORT);
        }
        else{
            Toast.makeText(this,"Permission DENIED",Toast.LENGTH_SHORT);
        }
    }
}