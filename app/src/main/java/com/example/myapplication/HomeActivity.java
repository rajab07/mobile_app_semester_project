package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class HomeActivity extends AppCompatActivity {

    // Product Images
    ImageView imgShirt, imgPant, imgJacket, imgTshirt, imgShoes1, imgShoes2;

    // Bottom Tabs
    LinearLayout btnHome, btnCart, btnProfile;

    // Right Drawer
    DrawerLayout drawerLayout;
    LinearLayout rightDrawer, drawerProfile, drawerSettings, drawerLogout;

    // User info
    String userName = "", userEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Get user info from LoginActivity
        userName = getIntent().getStringExtra("userName");
        userEmail = getIntent().getStringExtra("userEmail");

        // Initialize drawer
        drawerLayout = findViewById(R.id.drawerLayout);
        rightDrawer = findViewById(R.id.rightDrawer);
        drawerProfile = findViewById(R.id.drawerProfile);
        drawerSettings = findViewById(R.id.drawerSettings);
        drawerLogout = findViewById(R.id.drawerLogout);

        // Initialize product images
        imgShirt = findViewById(R.id.imgShirt);
        imgPant = findViewById(R.id.imgPant);
        imgJacket = findViewById(R.id.imgJacket);
        imgTshirt = findViewById(R.id.imgTshirt);
        imgShoes1 = findViewById(R.id.imgShoes1);
        imgShoes2 = findViewById(R.id.imgShoes2);

        // Initialize bottom tabs
        btnHome = findViewById(R.id.btnHome);
        btnCart = findViewById(R.id.btnCart);
        btnProfile = findViewById(R.id.btnProfile);

        // Product click listeners
        imgShirt.setOnClickListener(v -> openDetail("Shirt", R.drawable.shirt, 25));
        imgPant.setOnClickListener(v -> openDetail("Trouser shirt", R.drawable.suit, 30));
        imgJacket.setOnClickListener(v -> openDetail("Jacket", R.drawable.hoodie, 50));
        imgTshirt.setOnClickListener(v -> openDetail("T-Shirt", R.drawable.men, 20));
        imgShoes1.setOnClickListener(v -> openDetail("Coat", R.drawable.coat, 40));
        imgShoes2.setOnClickListener(v -> openDetail("Suit2", R.drawable.suit2, 45));

        // Bottom tabs
        btnHome.setOnClickListener(v ->
                Toast.makeText(this, "You are already on Home", Toast.LENGTH_SHORT).show()
        );

        btnCart.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, CartActivity1.class);
            startActivity(intent);
        });

        btnProfile.setOnClickListener(v -> {
            // Open right drawer
            drawerLayout.openDrawer(rightDrawer);
        });

        // Drawer clicks
        drawerProfile.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, ProfileActivity1.class);
            intent.putExtra("name", userName);
            intent.putExtra("email", userEmail);
            startActivity(intent);
            drawerLayout.closeDrawer(rightDrawer);
        });
// Inside onCreate(), after initializing your drawer items
        LinearLayout drawerSettings = findViewById(R.id.drawerSettings);

        drawerSettings.setOnClickListener(v -> {
            // Open SettingsActivity
            Intent intent = new Intent(HomeActivity.this, SettingsActivity.class);
            startActivity(intent);

            // Close the drawer after clicking
            drawerLayout.closeDrawer(GravityCompat.END);
        });


        drawerLogout.setOnClickListener(v -> {
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show();
            drawerLayout.closeDrawer(rightDrawer);

            // Go back to login
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }

    // Open Detail Activity
    private void openDetail(String name, int imageRes, int price) {
        Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("image", imageRes);
        intent.putExtra("price", price);
        startActivity(intent);
    }
}
