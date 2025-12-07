package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class RegisterActivity extends AppCompatActivity {

    Button btnRegister;
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize views
        btnRegister = findViewById(R.id.btnRegister);
        tvLogin = findViewById(R.id.tvLogin);

        // Navigate to Login screen
        tvLogin.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, com.example.myapplication.LoginActivity.class);
            startActivity(intent);
            finish(); // Optional: prevents going back to Register
        });

        // Example: Register button click
        btnRegister.setOnClickListener(v -> {
            // TODO: Add register logic (e.g., validation or Firebase auth)
        });
    }
}
