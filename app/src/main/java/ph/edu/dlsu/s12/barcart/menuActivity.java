package ph.edu.dlsu.s12.barcart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class menuActivity extends AppCompatActivity {
    public static final String NOTIFICATION_CHANNEL_ID = "10001";
    private final static String default_notification_channel_id = "default";
    ImageView btnDate;
    TextView cartDate;
    final Calendar myCalendar = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setOnNavigationItemSelectedListener(navListner);
        btnDate = findViewById(R.id.btnDate2);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new ScanFragment()).commit();
    }

    private void scheduleNotification(Notification notification, long delay) {

        Intent notificationIntent = new Intent(this, MyNotificationPublisher.class);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(MyNotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(menuActivity.this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, delay, pendingIntent);
        //alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, delay, pendingIntent);
    }

    private Notification getNotification(String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, default_notification_channel_id);
        builder.setContentTitle("Restock Cart Alert");
        builder.setContentText("Scheduled for: "+content);
        builder.setStyle(new NotificationCompat.BigTextStyle()
                .bigText("One or more carts are in need of restocking!"));

        builder.setSmallIcon(R.drawable.ic_notifications);
        builder.setAutoCancel(true);
        builder.setChannelId(NOTIFICATION_CHANNEL_ID);
        return builder.build();
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            updateLabel();
        }
    };

    public void setDate(View view) {
        new DatePickerDialog(
                menuActivity.this, date,
                myCalendar.get(Calendar.YEAR),
                myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
        ).show();
    }

    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());

        Date date = myCalendar.getTime();
        String alarmdate=sdf.format(date);
        cartDate = findViewById(R.id.notifDate);

        cartDate.setText("Last reminder: "+sdf.format(date));
        //trying something
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        long tmp=(calendar.getTimeInMillis() );
/*
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

 */
        scheduleNotification(getNotification(alarmdate), ((date.getTime())-tmp));
        //scheduleNotification(getNotification(btnDate.getText().toString()), date.getTime());
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListner =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.navScan:
                            selectedFragment = new ScanFragment();
                            break;
                        case R.id.navCart:
                            selectedFragment = new CartFragment();
                            break;
                        case R.id.navInventory:
                            selectedFragment = new InventoryFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, selectedFragment).commit();

                    return true;
                }
            };
}