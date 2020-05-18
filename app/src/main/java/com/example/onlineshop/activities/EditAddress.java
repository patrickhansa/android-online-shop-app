package com.example.onlineshop.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.onlineshop.R;
import com.example.onlineshop.views.EditAddressActivityViewImpl;

public class EditAddress extends AppCompatActivity {

    EditAddressActivityViewImpl editAddressActivityViewImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        editAddressActivityViewImpl = new EditAddressActivityViewImpl(EditAddress.this, null);
        setContentView(editAddressActivityViewImpl.getRootView());
        editAddressActivityViewImpl.initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        editAddressActivityViewImpl.bindDataToView();
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
                editAddressActivityViewImpl.onAccountDetailsSelected();
                return true;
            case R.id.logout_item:
                editAddressActivityViewImpl.onLogoutSelected();
                return true;
            case R.id.products_item:
                editAddressActivityViewImpl.onProductsSelected();
                return true;
            case R.id.shopping_cart_item:
                editAddressActivityViewImpl.onShoppingCartSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
