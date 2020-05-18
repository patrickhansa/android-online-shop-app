package com.example.onlineshop.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.onlineshop.R;
import com.example.onlineshop.views.MainActivityViewImpl;


public class MainActivity extends AppCompatActivity {

    MainActivityViewImpl mainActivityViewImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainActivityViewImpl = new MainActivityViewImpl(MainActivity.this, null);
        setContentView(mainActivityViewImpl.getRootView());
        mainActivityViewImpl.initViews();
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
                mainActivityViewImpl.onAccountDetailsSelected();
                return true;
            case R.id.logout_item:
                mainActivityViewImpl.onLogoutSelected();
                return true;
            case R.id.products_item:
                mainActivityViewImpl.onProductsSelected();
                return true;
            case R.id.shopping_cart_item:
                mainActivityViewImpl.onShoppingCartSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
