package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    ImageView imgProduct;
    TextView tvName, tvPrice, tvWebsite;
    Button btnAddCart, btnBuyNow;
    WebView webView;

    DatabaseHelper db;

    String userEmail;
    String name;
    int price;
    int imageRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Initialize database
        db = new DatabaseHelper(this);

        // Initialize views
        imgProduct = findViewById(R.id.imgProduct);
        tvName = findViewById(R.id.tvName);
        tvPrice = findViewById(R.id.tvPrice);
        btnAddCart = findViewById(R.id.btnAddCart);
        btnBuyNow = findViewById(R.id.btnBuyNow);
        tvWebsite = findViewById(R.id.tvWebsite);
        webView = findViewById(R.id.webView);

        // Get data from intent
        name = getIntent().getStringExtra("name");
        imageRes = getIntent().getIntExtra("image", 0);
        price = getIntent().getIntExtra("price", 0);
        userEmail = getIntent().getStringExtra("userEmail");

        // Set product details
        if (name == null) name = "Product";
        tvName.setText(name);
        tvPrice.setText("$" + price);
        if (imageRes != 0) imgProduct.setImageResource(imageRes);

        // Add to Cart
        btnAddCart.setOnClickListener(v -> {
            boolean inserted = db.addToCart(userEmail, name, price);
            if (inserted)
                Toast.makeText(this, name + " added to cart", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, "Failed to add to cart", Toast.LENGTH_SHORT).show();
        });

        // Buy Now
        btnBuyNow.setOnClickListener(v ->
                Toast.makeText(this, "Proceeding to buy " + name, Toast.LENGTH_SHORT).show()
        );

        // Open Daraz website in full-screen WebView
        tvWebsite.setOnClickListener(v -> {
            // Hide other views
            imgProduct.setVisibility(View.GONE);
            tvName.setVisibility(View.GONE);
            tvPrice.setVisibility(View.GONE);
            btnAddCart.setVisibility(View.GONE);
            btnBuyNow.setVisibility(View.GONE);
            tvWebsite.setVisibility(View.GONE);

            // Show WebView
            webView.setVisibility(View.VISIBLE);
            webView.setWebViewClient(new WebViewClient());
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl("https://www.daraz.pk");
        });
    }

    // Handle back press to hide WebView first
    @Override
    public void onBackPressed() {
        if (webView.getVisibility() == View.VISIBLE) {
            webView.setVisibility(View.GONE);

            // Show other views again
            imgProduct.setVisibility(View.VISIBLE);
            tvName.setVisibility(View.VISIBLE);
            tvPrice.setVisibility(View.VISIBLE);
            btnAddCart.setVisibility(View.VISIBLE);
            btnBuyNow.setVisibility(View.VISIBLE);
            tvWebsite.setVisibility(View.VISIBLE);
        } else {
            super.onBackPressed();
        }
    }
}
