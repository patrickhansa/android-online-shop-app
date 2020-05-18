package com.example.onlineshop.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.onlineshop.R;
import com.example.onlineshop.views.ProductListActivityViewImpl;

public class ProductList extends AppCompatActivity {

    ProductListActivityViewImpl productListActivityViewImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productListActivityViewImpl = new ProductListActivityViewImpl(ProductList.this, null);
        setContentView(productListActivityViewImpl.getRootView());
        productListActivityViewImpl.initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        productListActivityViewImpl.bindDataToView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem productsItem = menu.findItem(R.id.products_item);

        productsItem.setVisible(false);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.account_details_item:
                productListActivityViewImpl.onAccountDetailsSelected();
                return true;
            case R.id.logout_item:
                productListActivityViewImpl.onLogoutSelected();
                return true;
            case R.id.shopping_cart_item:
                productListActivityViewImpl.onShoppingCartSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
