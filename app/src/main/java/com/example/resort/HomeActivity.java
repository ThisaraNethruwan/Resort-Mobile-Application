package com.example.resort;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private Button roomBookingButton;
    private Button serviceReservationButton;
    private Button localAttractionsButton;
    private Button profileButton;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        roomBookingButton = findViewById(R.id.roomBookingButton);
        serviceReservationButton = findViewById(R.id.serviceReservationButton);
        localAttractionsButton = findViewById(R.id.localAttractionsButton);
        profileButton = findViewById(R.id.ProfileButton);
        logoutButton = findViewById(R.id.LogoutButton);


        String userEmail = getIntent().getStringExtra("USER_EMAIL");

        // Room Booking button click listener
        roomBookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, RoomBookingActivity.class);
                startActivity(intent);
            }
        });

        // Service Reservation button click listener
        serviceReservationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ServiceReservationActivity.class);
                startActivity(intent);
            }
        });

        // Local Attractions button click listener
        localAttractionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AttractionsActivity.class);
                startActivity(intent);
            }
        });

        // Profile button click listener
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userEmail != null && !userEmail.isEmpty()) {
                    // Pass the email to ProfileActivity when the button is clicked
                    Intent intent = new Intent(HomeActivity.this, Profile.class);
                    intent.putExtra("USER_EMAIL", userEmail);
                    startActivity(intent);
                } else {
                    // Handle case when no email is provided (user not logged in)
                    Toast.makeText(HomeActivity.this, "No user data provided!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Logout button click listener
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to MainActivity (login page)
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close HomeActivity so the user can't go back to it
            }
        });
    }
}
