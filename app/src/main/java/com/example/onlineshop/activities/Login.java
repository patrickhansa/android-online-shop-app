package com.example.onlineshop.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.onlineshop.views.LoginActivityViewImpl;

public class Login extends AppCompatActivity {

    LoginActivityViewImpl loginActivityViewImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginActivityViewImpl = new LoginActivityViewImpl(Login.this, null);
        setContentView(loginActivityViewImpl.getRootView());
        loginActivityViewImpl.checkRememberMe();
        loginActivityViewImpl.initViews();
    }
}
