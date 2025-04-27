package com.example.resort;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RoomBookingActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private TextView oceanViewDetailsTextView, deluxeRoomDetailsTextView;
    private EditText oceanViewGuestNameInput, deluxeRoomGuestNameInput;
    private Button oceanViewBookButton, deluxeRoomBookButton;
    private ImageView oceanViewImageView, deluxeRoomImageView;


    private int userId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_booking);

        // Initialize database helper
        dbHelper = new DatabaseHelper(this);

        // Initialize views
        oceanViewDetailsTextView = findViewById(R.id.oceanViewDetailsTextView);
        deluxeRoomDetailsTextView = findViewById(R.id.deluxeRoomDetailsTextView);
        oceanViewBookButton = findViewById(R.id.oceanViewBookButton);
        deluxeRoomBookButton = findViewById(R.id.deluxeRoomBookButton);
        oceanViewImageView = findViewById(R.id.oceanViewImageView);
        deluxeRoomImageView = findViewById(R.id.deluxeRoomImageView);
        oceanViewGuestNameInput = findViewById(R.id.oceanViewGuestNameInput);
        deluxeRoomGuestNameInput = findViewById(R.id.deluxeRoomGuestNameInput);

        // Room details
        oceanViewDetailsTextView.setText("Ocean View Suite\nPrice: Rs.30000/= per night\n");
        oceanViewImageView.setImageResource(R.drawable.ocean_view_suite);

        deluxeRoomDetailsTextView.setText("Deluxe Room\nPrice: Rs.20000/= per night\n");
        deluxeRoomImageView.setImageResource(R.drawable.deluxe_room);

        // Ocean View Booking
        oceanViewBookButton.setOnClickListener(v -> {
            String guestName = oceanViewGuestNameInput.getText().toString().trim();
            if (!guestName.isEmpty()) {
                String bookingDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                long bookingId = dbHelper.addBooking(userId, guestName, "Ocean View Suite", bookingDate);
                if (bookingId != -1) {
                    Toast.makeText(RoomBookingActivity.this, "Booking successful! Booking ID: " + bookingId, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RoomBookingActivity.this, "Failed to book room. Please try again.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(RoomBookingActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
            }
        });

        // Deluxe Room Booking
        deluxeRoomBookButton.setOnClickListener(v -> {
            String guestName = deluxeRoomGuestNameInput.getText().toString().trim();
            if (!guestName.isEmpty()) {
                String bookingDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                long bookingId = dbHelper.addBooking(userId, guestName, "Deluxe Room", bookingDate);
                if (bookingId != -1) {
                    Toast.makeText(RoomBookingActivity.this, "Booking successful! Booking ID: " + bookingId, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(RoomBookingActivity.this, "Failed to book room. Please try again.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(RoomBookingActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
