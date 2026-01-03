package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // ================= DATABASE =================
    private static final String DB_NAME = "shopping_app.db";
    private static final int DB_VERSION = 2;

    // ================= USERS TABLE =================
    public static final String TABLE_USERS = "users";
    public static final String U_ID = "id";
    public static final String U_NAME = "name";
    public static final String U_EMAIL = "email";
    public static final String U_PASSWORD = "password";

    // ================= CART TABLE =================
    public static final String TABLE_CART = "cart";
    public static final String C_ID = "id";
    public static final String C_EMAIL = "email";
    public static final String C_PRODUCT = "product_name";
    public static final String C_PRICE = "price";
    public static final String C_QUANTITY = "quantity";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // ================= CREATE TABLES =================
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_USERS_TABLE =
                "CREATE TABLE " + TABLE_USERS + " (" +
                        U_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        U_NAME + " TEXT," +
                        U_EMAIL + " TEXT UNIQUE," +
                        U_PASSWORD + " TEXT)";

        String CREATE_CART_TABLE =
                "CREATE TABLE " + TABLE_CART + " (" +
                        C_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        C_EMAIL + " TEXT," +
                        C_PRODUCT + " TEXT," +
                        C_PRICE + " INTEGER," +
                        C_QUANTITY + " INTEGER)";

        db.execSQL(CREATE_USERS_TABLE);
        db.execSQL(CREATE_CART_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        onCreate(db);
    }

    // ================= USER METHODS =================

    // REGISTER
    public boolean registerUser(String name, String email, String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(U_NAME, name);
        cv.put(U_EMAIL, email);
        cv.put(U_PASSWORD, password);

        long result = db.insert(TABLE_USERS, null, cv);
        return result != -1;
    }

    // LOGIN (FIXED)
    public boolean loginUser(String email, String password) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_USERS +
                        " WHERE " + U_EMAIL + "=? AND " + U_PASSWORD + "=?",
                new String[]{email, password}
        );

        boolean success = cursor.moveToFirst();
        cursor.close();
        return success;
    }

    // GET USER DETAILS
    public Cursor getUser(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT * FROM " + TABLE_USERS +
                        " WHERE " + U_EMAIL + "=?",
                new String[]{email}
        );
    }

    // ================= CART METHODS =================

    // ADD / UPDATE CART ITEM
    public boolean addToCart(String email, String product, int price) {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_CART +
                        " WHERE " + C_EMAIL + "=? AND " + C_PRODUCT + "=?",
                new String[]{email, product}
        );

        if (cursor.moveToFirst()) {
            int qty = cursor.getInt(cursor.getColumnIndexOrThrow(C_QUANTITY));
            ContentValues cv = new ContentValues();
            cv.put(C_QUANTITY, qty + 1);

            db.update(TABLE_CART, cv,
                    C_EMAIL + "=? AND " + C_PRODUCT + "=?",
                    new String[]{email, product});

            cursor.close();
            return true;

        } else {
            ContentValues cv = new ContentValues();
            cv.put(C_EMAIL, email);
            cv.put(C_PRODUCT, product);
            cv.put(C_PRICE, price);
            cv.put(C_QUANTITY, 1);

            long result = db.insert(TABLE_CART, null, cv);
            cursor.close();
            return result != -1;
        }
    }

    // GET CART ITEMS
    public Cursor getUserCart(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery(
                "SELECT * FROM " + TABLE_CART +
                        " WHERE " + C_EMAIL + "=?",
                new String[]{email}
        );
    }

    // REMOVE SINGLE ITEM
    public void removeItemFromCart(String email, String product) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CART,
                C_EMAIL + "=? AND " + C_PRODUCT + "=?",
                new String[]{email, product});
    }

    // CLEAR CART
    public void clearCart(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CART,
                C_EMAIL + "=?",
                new String[]{email});
    }
}
