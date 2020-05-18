package com.example.onlineshop.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.onlineshop.R;
import com.example.onlineshop.views.CheckoutActivityViewImpl;

public class Checkout extends AppCompatActivity {

    CheckoutActivityViewImpl checkoutActivityViewImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkoutActivityViewImpl = new CheckoutActivityViewImpl(Checkout.this, null);
        setContentView(checkoutActivityViewImpl.getRootView());
        checkoutActivityViewImpl.initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkoutActivityViewImpl.bindDataToView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.account_details_item:
                checkoutActivityViewImpl.onAccountDetailsSelected();
                return true;
            case R.id.logout_item:
                checkoutActivityViewImpl.onLogoutSelected();
                return true;
            case R.id.products_item:
                checkoutActivityViewImpl.onProductsSelected();
                return true;
            case R.id.shopping_cart_item:
                checkoutActivityViewImpl.onShoppingCartSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
