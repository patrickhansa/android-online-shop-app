package com.example.onlineshop.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.onlineshop.R;
import com.example.onlineshop.views.ShoppingCartActivityViewImpl;

public class ShoppingCart extends AppCompatActivity {

    ShoppingCartActivityViewImpl shoppingCartActivityViewImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shoppingCartActivityViewImpl = new ShoppingCartActivityViewImpl(ShoppingCart.this, null);
        setContentView(shoppingCartActivityViewImpl.getRootView());
        shoppingCartActivityViewImpl.initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        shoppingCartActivityViewImpl.bindDataToView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem shoppingCartItem = menu.findItem(R.id.shopping_cart_item);

        shoppingCartItem.setVisible(false);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.account_details_item:
                shoppingCartActivityViewImpl.onAccountDetailsSelected();
                return true;
            case R.id.logout_item:
                shoppingCartActivityViewImpl.onLogoutSelected();
                return true;
            case R.id.products_item:
                shoppingCartActivityViewImpl.onProductsSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
