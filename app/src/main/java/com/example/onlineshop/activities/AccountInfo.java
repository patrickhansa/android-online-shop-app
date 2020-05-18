package com.example.onlineshop.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.onlineshop.R;
import com.example.onlineshop.views.AccountInfoActivityViewImpl;

public class AccountInfo extends AppCompatActivity {

    AccountInfoActivityViewImpl accountInfoActivityViewImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountInfoActivityViewImpl = new AccountInfoActivityViewImpl(AccountInfo.this, null);
        setContentView(accountInfoActivityViewImpl.getRootView());
        accountInfoActivityViewImpl.initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        accountInfoActivityViewImpl.bindDataToView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem accountItem = menu.findItem(R.id.account_item);

        accountItem.setVisible(false);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.products_item:
                accountInfoActivityViewImpl.onProductsSelected();
                return true;
            case R.id.shopping_cart_item:
                accountInfoActivityViewImpl.onShoppingCartSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
