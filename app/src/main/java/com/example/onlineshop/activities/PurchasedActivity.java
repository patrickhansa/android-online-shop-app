package com.example.onlineshop.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.onlineshop.views.PurchasedActivityViewImpl;

public class PurchasedActivity extends AppCompatActivity {

    PurchasedActivityViewImpl purchasedActivityViewImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        purchasedActivityViewImpl = new PurchasedActivityViewImpl(PurchasedActivity.this, null);
        setContentView(purchasedActivityViewImpl.getRootView());
        purchasedActivityViewImpl.initViews();
    }
}
