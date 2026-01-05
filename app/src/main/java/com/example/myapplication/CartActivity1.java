package com.example.myapplication;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CartActivity1 extends AppCompatActivity {

    LinearLayout cartItemsContainer;
    DatabaseHelper db;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cartItemsContainer = findViewById(R.id.cartItemsContainer);
        db = new DatabaseHelper(this);

        // Get logged-in user email
        userEmail = getIntent().getStringExtra("userEmail");

        loadCartItems();
    }

    private void loadCartItems() {

        cartItemsContainer.removeAllViews();

        Cursor cursor = db.getUserCart(userEmail);

        if (cursor == null || cursor.getCount() == 0) {

            TextView tvEmpty = new TextView(this);
            tvEmpty.setText("Your cart is empty");
            tvEmpty.setTextSize(18f);
            tvEmpty.setPadding(16, 16, 16, 16);
            cartItemsContainer.addView(tvEmpty);

        } else {

            while (cursor.moveToNext()) {

                String productName =
                        cursor.getString(cursor.getColumnIndexOrThrow("product_name"));
                int price =
                        cursor.getInt(cursor.getColumnIndexOrThrow("price"));
                int quantity =
                        cursor.getInt(cursor.getColumnIndexOrThrow("quantity"));

                TextView tvItem = new TextView(this);
                tvItem.setText(productName + "  x" + quantity + "  - $" + price);
                tvItem.setTextSize(16f);
                tvItem.setPadding(16, 16, 16, 16);

                cartItemsContainer.addView(tvItem);
            }
        }

        if (cursor != null) cursor.close();
    }


    @Override
    protected void onResume() {
        super.onResume();
        loadCartItems();
    }
}
