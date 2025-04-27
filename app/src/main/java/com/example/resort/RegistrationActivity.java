package com.example.resort;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText emailInput, passwordInput, fullNameInput, phoneNumberInput;
    private Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        dbHelper = new DatabaseHelper(this);
        initializeViews();

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void initializeViews() {
        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        fullNameInput = findViewById(R.id.fullNameInput);
        phoneNumberInput = findViewById(R.id.phoneNumberInput);
        registerButton = findViewById(R.id.registerButton);
    }

    private void registerUser() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        String fullName = fullNameInput.getText().toString().trim();
        String phoneNumber = phoneNumberInput.getText().toString().trim();

        if (validateInputs(email, password, fullName, phoneNumber)) {
            dbHelper.addUser(email, password, fullName, phoneNumber);
            Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            finish();
        }
    }

    private boolean validateInputs(String email, String password, String fullName, String phoneNumber) {
        if (TextUtils.isEmpty(email)) {
            emailInput.setError("Email is required");
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            passwordInput.setError("Password is required");
            return false;
        }
        if (TextUtils.isEmpty(fullName)) {
            fullNameInput.setError("Full name is required");
            return false;
        }
        if (TextUtils.isEmpty(phoneNumber)) {
            phoneNumberInput.setError("Phone number is required");
            return false;
        }
        return true;
    }
}
