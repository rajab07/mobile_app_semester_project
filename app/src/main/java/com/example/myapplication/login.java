package com.example.myapplication;

import android.app.Activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class login extends Activity { @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.login);  // <-- this links to login.xml
}
}
