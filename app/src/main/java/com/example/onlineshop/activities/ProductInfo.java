package com.example.onlineshop.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.onlineshop.R;
import com.example.onlineshop.views.ProductInfoActivityViewImpl;

public class ProductInfo extends AppCompatActivity {

    ProductInfoActivityViewImpl productInfoActivityViewImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productInfoActivityViewImpl = new ProductInfoActivityViewImpl(
                ProductInfo.this, null, getIntent().getLongExtra("product_id", 0));
        setContentView(productInfoActivityViewImpl.getRootView());
        productInfoActivityViewImpl.initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        productInfoActivityViewImpl.bindDataToView();
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
                productInfoActivityViewImpl.onAccountDetailsSelected();
                return true;
            case R.id.logout_item:
                productInfoActivityViewImpl.onLogoutSelected();
                return true;
            case R.id.products_item:
                productInfoActivityViewImpl.onProductsSelected();
                return true;
            case R.id.shopping_cart_item:
                productInfoActivityViewImpl.onShoppingCartSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
