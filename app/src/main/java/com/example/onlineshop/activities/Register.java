package com.example.onlineshop.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.onlineshop.views.RegisterActivityViewImpl;

public class Register extends AppCompatActivity {

    RegisterActivityViewImpl registerActivityViewImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerActivityViewImpl = new RegisterActivityViewImpl(Register.this, null);
        setContentView(registerActivityViewImpl.getRootView());
        registerActivityViewImpl.initViews();
    }
}
