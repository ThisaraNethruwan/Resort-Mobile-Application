package com.example.resort;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ServiceReservationActivity extends AppCompatActivity {

    private EditText nameEditText;
    private Spinner serviceTypeSpinner;
    private DatabaseHelper1 databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_reservation);

        // Initialize views
        nameEditText = findViewById(R.id.nameEditText);
        serviceTypeSpinner = findViewById(R.id.serviceTypeSpinner);
        databaseHelper = new DatabaseHelper1(this);
    }

    // Method to handle the service reservation submission
    public void reserveService(View view) {
        String name = nameEditText.getText().toString().trim();
        String serviceType = serviceTypeSpinner.getSelectedItem().toString();

        // Validate inputs
        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            return;
        }

        // Save service reservation to database
        long result = databaseHelper.addServiceReservation(name, serviceType);

        // Check if insertion was successful
        if (result != -1) {
            Toast.makeText(this, "Service reserved successfully!", Toast.LENGTH_SHORT).show();
            nameEditText.setText("");  // Clear the input field after submission
        } else {
            Toast.makeText(this, "Failed to reserve service. Try again.", Toast.LENGTH_SHORT).show();
        }
    }

    // Method to retrieve and display all service reservations (optional, for debugging)
    private void displayServiceReservations() {
        Cursor cursor = databaseHelper.getAllServiceReservations();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper1.COLUMN_SERVICE_RESERVATION_NAME));
                String serviceType = cursor.getString(cursor.getColumnIndex(DatabaseHelper1.COLUMN_SERVICE_TYPE));

            }
            cursor.close();
        }
    }
}
