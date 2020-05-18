package com.example.onlineshop.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.onlineshop.R;
import com.example.onlineshop.views.EditAccountActivityViewImpl;

public class EditAccount extends AppCompatActivity {

    EditAccountActivityViewImpl editAccountActivityViewImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editAccountActivityViewImpl = new EditAccountActivityViewImpl(EditAccount.this, null);
        setContentView(editAccountActivityViewImpl.getRootView());
        editAccountActivityViewImpl.initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        editAccountActivityViewImpl.bindDataToView();
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
                editAccountActivityViewImpl.onAccountDetailsSelected();
                return true;
            case R.id.logout_item:
                editAccountActivityViewImpl.onLogoutSelected();
                return true;
            case R.id.products_item:
                editAccountActivityViewImpl.onProductsSelected();
                return true;
            case R.id.shopping_cart_item:
                editAccountActivityViewImpl.onShoppingCartSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
