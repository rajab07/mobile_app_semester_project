package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    Button btnToggleTheme, btnToggleLanguage;
    TextView tvTheme, tvLanguage;

    SharedPreferences preferences;
    boolean isDarkTheme;
    boolean isUrduLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences("appSettings", MODE_PRIVATE);

        // Load saved theme
        isDarkTheme = preferences.getBoolean("darkTheme", false);
        if (isDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        // Load saved language
        isUrduLanguage = preferences.getBoolean("isUrdu", false);
        setLanguage(isUrduLanguage);

        setContentView(R.layout.activity_settings);

        tvTheme = findViewById(R.id.tvTheme);
        tvLanguage = findViewById(R.id.tvLanguage);
        btnToggleTheme = findViewById(R.id.btnToggleTheme);
        btnToggleLanguage = findViewById(R.id.btnToggleLanguage);

        btnToggleTheme.setText(isDarkTheme ? "Dark" : "Light");
        btnToggleLanguage.setText(isUrduLanguage ? "اردو" : "English");

        // Toggle Theme
        btnToggleTheme.setOnClickListener(v -> {
            isDarkTheme = !isDarkTheme;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("darkTheme", isDarkTheme);
            editor.apply();

            AppCompatDelegate.setDefaultNightMode(isDarkTheme ?
                    AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
            recreate(); // reload activity to apply theme
        });

        // Toggle Language
        btnToggleLanguage.setOnClickListener(v -> {
            isUrduLanguage = !isUrduLanguage;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isUrdu", isUrduLanguage);
            editor.apply();

            setLanguage(isUrduLanguage);
            recreate(); // reload activity to apply language
        });
    }

    private void setLanguage(boolean urdu) {
        Locale locale = urdu ? new Locale("ur") : Locale.ENGLISH;
        Locale.setDefault(locale);
        android.content.res.Configuration config = new android.content.res.Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }
}
