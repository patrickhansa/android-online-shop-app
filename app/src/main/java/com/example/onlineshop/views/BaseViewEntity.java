package com.example.onlineshop.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.onlineshop.activities.AccountInfo;
import com.example.onlineshop.activities.Checkout;
import com.example.onlineshop.activities.EditAccount;
import com.example.onlineshop.activities.EditAddress;
import com.example.onlineshop.activities.Login;
import com.example.onlineshop.activities.MainActivity;
import com.example.onlineshop.activities.ProductList;
import com.example.onlineshop.activities.PurchasedActivity;
import com.example.onlineshop.activities.Register;
import com.example.onlineshop.activities.ShoppingCart;

import static android.content.Context.MODE_PRIVATE;

public class BaseViewEntity {
    View rootView;

    public void navigateToLoginActivity() {
        Intent intent = new Intent(rootView.getContext(), Login.class);
        rootView.getContext().startActivity(intent);
    }

    public void navigateToMainActivity() {
        Intent intent = new Intent(rootView.getContext(), MainActivity.class);
        rootView.getContext().startActivity(intent);
    }

    public void navigateToRegisterActivity() {
        Intent intent = new Intent(rootView.getContext(), Register.class);
        rootView.getContext().startActivity(intent);
    }

    public void navigateToEditAccountActivity() {
        Intent intent = new Intent(rootView.getContext(), EditAccount.class);
        rootView.getContext().startActivity(intent);
    }

    public void navigateToAccountInfoActivity() {
        Intent intent = new Intent(rootView.getContext(), AccountInfo.class);
        rootView.getContext().startActivity(intent);
    }

    public void navigateToProductListActivity() {
        Intent intent = new Intent(rootView.getContext(), ProductList.class);
        rootView.getContext().startActivity(intent);
    }

    public void navigateToShoppingCartActivity() {
        Intent intent = new Intent(rootView.getContext(), ShoppingCart.class);
        rootView.getContext().startActivity(intent);
    }

    public void navigateToCheckoutActivity() {
        Intent intent = new Intent(rootView.getContext(), Checkout.class);
        rootView.getContext().startActivity(intent);
    }

    public void navigateToEditAddressActivity() {
        Intent intent = new Intent(rootView.getContext(), EditAddress.class);
        rootView.getContext().startActivity(intent);
    }

    public void navigateToPurchasedActivity() {
        Intent intent = new Intent(rootView.getContext(), PurchasedActivity.class);
        rootView.getContext().startActivity(intent);
    }

    public void onAccountDetailsSelected() {
        navigateToAccountInfoActivity();
    }

    public void onLogoutSelected() {
        SharedPreferences preferences =
                rootView.getContext().getSharedPreferences("checkbox", MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("remember", "false");
        editor.apply();

        navigateToLoginActivity();
    }

    public void onProductsSelected() {
        navigateToProductListActivity();
    }

    public void onShoppingCartSelected() {
        navigateToShoppingCartActivity();
    }

    public void showToast(String message) {
        Toast.makeText(rootView.getContext(), message, Toast.LENGTH_LONG).show();
    }

    /**
     * Shows the progress UI and hides the main form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show, View mMainFormView, View mProgressView, TextView tvLoad) {
        int shortAnimTime = 200;

        mMainFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        mMainFormView.animate().setDuration(shortAnimTime).alpha(
                show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mMainFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        });

        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
        mProgressView.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });

        tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
        tvLoad.animate().setDuration(shortAnimTime).alpha(
                show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                tvLoad.setVisibility(show ? View.VISIBLE : View.GONE);
            }
        });
    }
}
