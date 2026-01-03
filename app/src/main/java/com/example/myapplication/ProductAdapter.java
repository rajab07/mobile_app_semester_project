package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

 class detailActivity extends AppCompatActivity {

    ImageView imgProduct;
    TextView tvName, tvPrice;
    Button btnAddCart, btnBuyNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgProduct = findViewById(R.id.imgProduct);
        tvName = findViewById(R.id.tvName);
        tvPrice = findViewById(R.id.tvPrice);
        btnAddCart = findViewById(R.id.btnCart);
        btnBuyNow = findViewById(R.id.btnBuyNow);

        String name = getIntent().getStringExtra("name");
        int imageRes = getIntent().getIntExtra("image", 0);
        int price = getIntent().getIntExtra("price", 0);

        imgProduct.setImageResource(imageRes);
        tvName.setText(name);
        tvPrice.setText("$" + price);

        btnAddCart.setOnClickListener(v ->
                Toast.makeText(this, name + " added to cart", Toast.LENGTH_SHORT).show());

        btnBuyNow.setOnClickListener(v ->
                Toast.makeText(this, "Proceed to buy " + name, Toast.LENGTH_SHORT).show());
    }
}
