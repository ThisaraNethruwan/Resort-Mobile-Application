package com.example.resort;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AttractionsActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "LuxeVistaNotifications";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attractions);

        // Find the Enable Notifications button
        Button enableNotificationsButton = findViewById(R.id.enableNotificationsButton);

        // Set up a click listener for the button
        enableNotificationsButton.setOnClickListener(v -> {
            createNotificationChannel();
            Toast.makeText(this, "Notifications enabled for LuxeVista updates!", Toast.LENGTH_SHORT).show();
        });
    }

    // Method to create a notification channel for special updates (API level 26+)
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "LuxeVista Notifications";
            String description = "Notifications for hotel events, discounts, and updates.";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);

            // Register the channel with the system
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}
