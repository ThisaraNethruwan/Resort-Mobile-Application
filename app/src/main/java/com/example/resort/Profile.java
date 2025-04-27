package com.example.resort;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private TextView profileName, profileEmail, profilePhone, bookingsText;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize views
        profileName = findViewById(R.id.profileName);
        profileEmail = findViewById(R.id.profileEmail);
        profilePhone = findViewById(R.id.profilePhone);
        bookingsText = findViewById(R.id.bookingsText);
        logoutButton = findViewById(R.id.LogoutButton);

        dbHelper = new DatabaseHelper(this);


        String userEmail = getIntent().getStringExtra("USER_EMAIL");

        if (userEmail != null && !userEmail.isEmpty()) {
            // Fetch user details by email
            Cursor cursor = dbHelper.getUser(userEmail);
            if (cursor != null && cursor.moveToFirst()) {
                // Get column values safely
                String fullName = cursor.getString(cursor.getColumnIndexOrThrow("fullName"));
                String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
                String phoneNumber = cursor.getString(cursor.getColumnIndexOrThrow("phoneNumber"));

                // Display user details
                profileEmail.setText("Email: " + email);
                profileName.setText("Name: " + (fullName != null ? fullName : "N/A"));
                profilePhone.setText("Phone: " + (phoneNumber != null ? phoneNumber : "N/A"));
                bookingsText.setText("Welcome to LuxeVista!");

                cursor.close();
            } else {
                // Handle case when no user is found for the provided email
                profileName.setText(userEmail.split("@")[0]);
                bookingsText.setText("No additional info available.");
            }
        } else {
            // Handle the case when no email is provided
            Toast.makeText(this, "No user data provided!", Toast.LENGTH_SHORT).show();
            finish();  // Close the activity
        }
        // Logout button click listener
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Profile.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
