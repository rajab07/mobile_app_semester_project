package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    ImageView imgProduct;
    TextView tvName, tvPrice;
    Button btnAddCart, btnBuyNow;

    DatabaseHelper db;

    String userEmail;
    String name;
    int price;
    int imageRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Initialize DB
        db = new DatabaseHelper(this);

        // Initialize views
        imgProduct = findViewById(R.id.imgProduct);
        tvName = findViewById(R.id.tvName);
        tvPrice = findViewById(R.id.tvPrice);
        btnAddCart = findViewById(R.id.btnAddCart);
        btnBuyNow = findViewById(R.id.btnBuyNow);

        // Get data from intent
        name = getIntent().getStringExtra("name");
        imageRes = getIntent().getIntExtra("image", 0); // optional, for display only
        price = getIntent().getIntExtra("price", 0);
        userEmail = getIntent().getStringExtra("userEmail");

        // Set data
        imgProduct.setImageResource(imageRes);
        tvName.setText(name);
        tvPrice.setText("$" + price);

        // Add to Cart
        btnAddCart.setOnClickListener(v -> {

            boolean inserted = db.addToCart(
                    userEmail,
                    name,
                    price
            );

            if (inserted) {
                Toast.makeText(this, name + " added to cart", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to add to cart", Toast.LENGTH_SHORT).show();
            }
        });

        // Buy Now
        btnBuyNow.setOnClickListener(v ->
                Toast.makeText(this, "Proceeding to buy " + name, Toast.LENGTH_SHORT).show()
        );
    }
}
