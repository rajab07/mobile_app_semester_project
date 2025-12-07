package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    TextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Initialize views
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        // Navigate to Register screen when text clicked
        tvRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, com.example.myapplication.RegisterActivity.class);
            startActivity(intent);
        });

        // Example: Login button click
        btnLogin.setOnClickListener(v -> {
            // TODO: Add login validation here
        });
    }
}
